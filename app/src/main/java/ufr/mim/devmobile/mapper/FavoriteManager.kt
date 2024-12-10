package ufr.mim.devmobile.mapper

import androidx.compose.runtime.mutableStateMapOf

object FavoriteManager {
    val favorites = mutableStateMapOf<String, Boolean>()

    fun toggleFavorite(id: String) {
        favorites[id] = favorites[id] != true
    }

    fun isFavorite(item: String) = favorites[item] == true
}