package ufr.mim.devmobile.data

import kotlinx.serialization.json.Json
import ufr.mim.devmobile.mapper.BookMapper
import ufr.mim.devmobile.model.BookDto
import ufr.mim.devmobile.model.Books

object BookRepository {
    private val bookMapper = BookMapper()

    val bookList: MutableList<Books> by lazy {
        val bookListDto: BookDto = Json.decodeFromString(bookData)
        bookListDto.books.map { bookMapper.mapBookDtoToBook(it) }.toMutableList()
    }
}