package ufr.mim.devmobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.BookInList
import ufr.mim.devmobile.data.DataList
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.viewmodel.FavoriteViewModel


@SuppressLint("MutableCollectionMutableState")
@Composable
fun LibrairyScreen(nameList: String,
                   onViewDetails: (String) -> Unit,
                   favoriteViewModel: FavoriteViewModel
) {
    val favorites by favoriteViewModel.favoriteBooks.collectAsState()

    val bookList by remember { mutableStateOf(BookRepository.bibliotheque.toMutableList()) }
    val books = nameList.listChoice(favorites)

    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(books.size / 2 + books.size % 2) { rowIndex ->

            Row (
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Premier élément de la colonne
                val firstIndex = rowIndex * 2
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(300.dp)
                ) {
                    BookInList(
                        book = books[firstIndex],
                        favoriteViewModel = favoriteViewModel,
                        nameList = nameList,
                        onViewDetails = onViewDetails
                    )
                }

                // Deuxième élément de la colonne (s'il existe)
                if (firstIndex + 1 < books.size) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(300.dp)
                    ) {
                        BookInList(
                            book = books[firstIndex + 1],
                            favoriteViewModel = favoriteViewModel,
                            nameList = nameList,
                            onViewDetails = onViewDetails
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun String.listChoice(favorites: Set<String>) : MutableList<Books> {
    val fav: MutableList<Int> = favorites.mapNotNull { it.toIntOrNull() }.toMutableList()

    var listReturn = mutableListOf<Books>()
    var listInt = mutableListOf<Int>()
    when (this) {
        "Ma bibliothèque" -> { listReturn = BookRepository.bibliotheque }
        "Livres en cours" -> { listInt = DataList.listEnCours }
        "Livres en attente" -> { listInt = DataList.listAttente }
        "Suggestions" -> { listInt = DataList.suggestion }
        "Romance" -> { listInt = DataList.romance }
        "Science-fiction" -> { listInt = DataList.science }
        "Policier" -> { listInt = DataList.policier }
        "Livres Favoris" -> { listInt = fav }
        "Livres à acheter" -> { listInt = DataList.listAcheter }
        "Livres déjà lus" -> { listInt = DataList.listLu }
        else -> { listReturn = BookRepository.bookList }
    }
    if (listReturn.isEmpty()) {
        BookRepository.bookList.forEach { book ->
            if (book.id in listInt) {
                listReturn.add(book)
            }
        }
    }

    return listReturn
}
