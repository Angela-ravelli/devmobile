package ufr.mim.devmobile.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    private val _favoriteBooks = MutableStateFlow<Set<String>>(emptySet())
    val favoriteBooks = _favoriteBooks.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            dataStoreManager.favoriteBooks.collect { favorites ->
                _favoriteBooks.value = favorites
            }
        }
    }

    fun toggleFavorite(item: String) {
        viewModelScope.launch {
            dataStoreManager.toggleFavorite(item)
        }
    }
}

