package ufr.mim.devmobile.mapper

import kotlinx.serialization.json.Json
import ufr.mim.devmobile.data.bookData
import ufr.mim.devmobile.model.BookDto
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.viewmodel.BookViewModel

object BookRepository {
    private val bookMapper = BookMapper()

    val bookList: MutableList<Books> by lazy {
        val bookListDto: BookDto = Json.decodeFromString(bookData)
        bookListDto.books.map { bookMapper.mapBookDtoToBook(it) }.toMutableList()
    }
}