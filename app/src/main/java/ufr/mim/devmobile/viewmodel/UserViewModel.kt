package ufr.mim.devmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _userName = MutableStateFlow<String?>(null)
    val userName = _userName.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreManager.userName.collect { name ->
                _userName.value = name
            }
        }
    }

    fun saveUserName(name: String) {
        viewModelScope.launch {
            dataStoreManager.saveUserName(name)
        }
    }
}
