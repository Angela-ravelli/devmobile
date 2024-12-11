package ufr.mim.devmobile.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
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
        private val PROGRESS_KEY = intPreferencesKey("reading_progress")
        //private val BOOK_KEY = stringSetPreferencesKey("all_books")
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

    // *** Gestion de la Progression ***

    // Charger la progression actuelle
    val readingProgress: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[PROGRESS_KEY] ?: 0
        }

    // Sauvegarder la progression
    suspend fun saveProgress(pages: Int) {
        context.dataStore.edit { preferences ->
            preferences[PROGRESS_KEY] = pages
        }
    }

    // *** Gestion des livres ***

    // Charger la liste des livres
    /*val allBooks: Flow<Set<Books>> = context.dataStore.data
        .map { preferences ->
            preferences[BOOK_KEY] ?: emptySet()
        }*/
}