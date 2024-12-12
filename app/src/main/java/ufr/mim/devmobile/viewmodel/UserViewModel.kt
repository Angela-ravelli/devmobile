package ufr.mim.devmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

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
