package ufr.mim.devmobile.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ufr.mim.devmobile.model.Books

class BooksViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    /*private val _allBooks = MutableStateFlow<Set<Books>>(emptySet())
    val allBooks = _allBooks.asStateFlow()

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            dataStoreManager.allBooks.collect { book ->
                _allBooks.value = book
            }
        }
    }*/

    //Utile ???
    /*fun toggleBooks(item: String) {
        viewModelScope.launch {
            dataStoreManager.toggleFavorite(item)
        }
    }*/
}