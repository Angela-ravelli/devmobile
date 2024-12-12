package ufr.mim.devmobile.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.ProgressInput
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.mapper.mapToMyImageResource
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun DetailsScreen(
    id: String?,
    favoriteViewModel: FavoriteViewModel,
) {
    val isFav by favoriteViewModel.favoriteBooks.collectAsState()
    if (id != null) {
        Log.d("ID : ", id)
    }
    val idd = (id?.toInt() ?: 0) -1
    
    LazyColumn(
        modifier = Modifier.padding(MainPadding),
        verticalArrangement = Arrangement.spacedBy(MainPadding)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (id != null) {
                    Text(
                        text = BookRepository.bookList[idd].title,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.weight(1f)
                    )
                }

                IconButton(
                    onClick = {
                        favoriteViewModel.toggleFavorite(id.toString())
                    }
                ) {
                    Icon(
                        imageVector =
                        if (isFav.contains(id.toString()))
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,
                        contentDescription =
                        if (isFav.contains(id.toString()))
                            "Supprimer des favoris"
                        else
                            "Ajouter aux favoris",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
        item {
            if (id != null) {
                Text(
                    text = "Auteur : "+ BookRepository.bookList[idd].author
                )
            }
        }
        item {
            if (id != null) {
                Image(
                    painter = painterResource(id = BookRepository.bookList[idd].image.mapToMyImageResource()),
                        contentDescription = "Couverture",
                        modifier = Modifier
                            .size(500.dp)
                    )
            }
        }
        item {
            if (id != null) {
                Text(
                    text = "Résumé : "+ BookRepository.bookList[idd].plot
                )
            }
        }
        item {
            if (id != null) {
                Text(
                    text = "Genre : "+ BookRepository.bookList[idd].genre.toString()
                )
            }
        }
        item {
            if (id != null) {
                Text(
                    text = "Année de sortie : "+ BookRepository.bookList[idd].year.toString()
                )
            }
        }
        item {
            if (id != null) {
                Text(
                    text = "Editeur : "+ BookRepository.bookList[idd].editor
                )
            }
        }
        item {
            if (id != null) {
                Text(
                    text = "Nombre de pages : "+ BookRepository.bookList[idd].pages.toString()
                )
            }
        }
        item {
            Row {
                if (id != null) {
                    Text(
                        text = "Etiquette : "+ BookRepository.bookList[idd].etiquette.toString()
                    )
                }
                Card {  }
            }
        }
        item {

            ProgressInput(
                progressPages = BookRepository.bookList[idd].progression.toString(),
                onValueChange = { pages ->
                    val pagesInt = pages.toIntOrNull() ?: 0
                    // Mise à jour de la progression du livre
                    BookRepository.updateBookProgress(
                        BookRepository.bookList[idd].id.toString(), pagesInt
                    )
                },
                nbpages = BookRepository.bookList[idd].pages.toString()
            )
        }
    }
}