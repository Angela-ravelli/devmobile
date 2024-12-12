package ufr.mim.devmobile.viewmodel

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.stringSetPreferencesKey
import ufr.mim.devmobile.model.Books

// Extension DataStore
val Context.dataStore by preferencesDataStore("user_preferences")

class DataStoreManager(private val context: Context) {

    companion object {
        // Clés des préférences
        private val FAVORITES_KEY = stringSetPreferencesKey("favorite_books")
        val USER_NAME = stringPreferencesKey("user_name")
    }

    // *** Gestion des Favoris ***

    // Charger la liste des favoris
    val favoriteBooks: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[FAVORITES_KEY] ?: emptySet()
        }

    // Ajouter ou retirer un favori
    suspend fun toggleFavorite(item: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITES_KEY] ?: emptySet()
            val updatedFavorites = if (currentFavorites.contains(item)) {
                currentFavorites - item  // Supprimer
            } else {
                currentFavorites + item  // Ajouter
            }
            preferences[FAVORITES_KEY] = updatedFavorites
        }
    }

    // *** Gestion du prénom ***

    val userName: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[USER_NAME] }

    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }
    }
}