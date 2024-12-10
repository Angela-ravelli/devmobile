package ufr.mim.devmobile.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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
            dataStoreManager.progressPages.collect { pages ->
                _progressPages.value = pages
            }
        }
    }

    fun saveProgress(pages: Int) {
        viewModelScope.launch {
            dataStoreManager.saveProgress(pages)
        }
    }
}

