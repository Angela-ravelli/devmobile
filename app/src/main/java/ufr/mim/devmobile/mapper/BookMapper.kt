package ufr.mim.devmobile.mapper

import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.model.SingleBookDto

class BookMapper {
    fun mapBookDtoToBook(bookDto: SingleBookDto): Books {
        return with(bookDto) {
            Books(
                id = id,
                title = title,
                year = year,
                genre = genre.split(", "),
                author = author,
                plot = plot,
                progression = progression,
                pages = pages,
                etiquette = etiquette.split(", "),
                editor = editor,
                image = image
            )
        }
    }
}