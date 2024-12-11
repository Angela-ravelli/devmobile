package ufr.mim.devmobile.model

import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val books: List<SingleBookDto>
)

@Serializable
data class SingleBookDto (
    val id: Int,
    val title: String,
    val year : Int,
    val genre: String,
    val author: String,
    val plot: String,
    val progression: Int,
    val pages: Int,
    val etiquette: String,
    val editor: String,
    val image: String
)