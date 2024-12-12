package ufr.mim.devmobile.model

import kotlinx.serialization.Serializable

@Serializable
data class Books(
    val id: Int,
    val title: String,
    val year : Int,
    val genre: List<String>,
    val author: String,
    val plot: String,
    var progression: Int,
    val pages: Int,
    val etiquette: List<String>,
    val editor: String,
    val image: String
)