package ufr.mim.devmobile.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ufr.mim.devmobile.mapper.BookMapper
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.model.Books

object BookViewModel : ViewModel() {

    private val _books = MutableStateFlow<List<Books>>(emptyList())
    val bookList = _books.asStateFlow()

    // Initialiser la liste avec le JSon
    fun initializeBooks() {
        _books.value = BookRepository.bookList.toMutableList()
    }

    // Ajouter un nouveau livre
    fun addBook(book: Books) {
        _books.value = _books.value.toMutableList().apply {
            add(book)
        }
    }
}
