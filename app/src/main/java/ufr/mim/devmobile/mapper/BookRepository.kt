package ufr.mim.devmobile.mapper

import kotlinx.serialization.json.Json
import ufr.mim.devmobile.data.bookData
import ufr.mim.devmobile.model.BookDto
import ufr.mim.devmobile.model.Books

object BookRepository {
    private val bookMapper = BookMapper()

    val bookList: MutableList<Books> by lazy {
        val bookListDto: BookDto = Json.decodeFromString(bookData)
        bookListDto.books.map { bookMapper.mapBookDtoToBook(it) }.toMutableList()
    }


    fun updateBookProgress(bookId: String, newProgress: Int) {
        val book = bookList.find { book -> book.id.toString() == bookId }
        book?.let { book ->
            book.progression = newProgress
        }
    }
}