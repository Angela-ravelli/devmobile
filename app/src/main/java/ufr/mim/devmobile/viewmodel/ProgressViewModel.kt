package ufr.mim.devmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProgressViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    private val _progressPages = MutableStateFlow(0)
    val progressPages = _progressPages.asStateFlow()

    init {
        loadProgress()
    }

    private fun loadProgress() {
        viewModelScope.launch {
            dataStoreManager.readingProgress.collect { progress ->
                _progressPages.value = progress
            }
        }
    }

    fun saveProgress(pages: Int) {
        viewModelScope.launch {
            dataStoreManager.saveProgress(pages)
        }
    }
}

