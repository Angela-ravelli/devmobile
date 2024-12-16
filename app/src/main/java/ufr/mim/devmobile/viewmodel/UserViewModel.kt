package ufr.mim.devmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    // Il vaut mieux passer un State (qui est une data class)
    // plutôt qu'un string
    // car c'est plus facile de le modifier par la suite
    private val userMutableStateFlow = MutableStateFlow(UserState())
    val stateFlow: StateFlow<UserState>
        get() = userMutableStateFlow.asStateFlow()
    internal var userState: UserState
        get() = userMutableStateFlow.value
        set(value) {
            userMutableStateFlow.value = value
        }

    init {
        userState = userState.copy(isLoading = true)
        viewModelScope.launch {
            dataStoreManager.userName.collect { name ->
                // On remplace tout le state pour le @Composable voit le changement
                userState = userState.copy(
                    userName = name,
                    isLoading = true
                )
            }
        }
    }

    fun saveUserName(name: String) {
        userState = userState.copy(isLoading = true)
        viewModelScope.launch {
            dataStoreManager.saveUserName(name)
            userState = userState.copy(
                userName = name,
                isLoading = true
            )
        }
    }
}
