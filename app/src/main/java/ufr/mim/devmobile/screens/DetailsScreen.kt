package ufr.mim.devmobile.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.ProgressInput
import ufr.mim.devmobile.data.DataList
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.mapper.mapToMyImageResource
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun DetailsScreen(
    id: String,
    favoriteViewModel: FavoriteViewModel,
) {
    val isFav by favoriteViewModel.favoriteBooks.collectAsState()

    val idd = id.toInt() -1
    
    LazyColumn(
        modifier = Modifier.padding(MainPadding),
        verticalArrangement = Arrangement.spacedBy(MainPadding)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = BookRepository.bibliotheque[idd].title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        if (isFav.contains(id)) {
                            DataList.listFavoris.remove(id.toInt())
                        } else {
                            DataList.listFavoris.add(id.toInt())
                        }
                        favoriteViewModel.toggleFavorite(id)
                    }
                ) {
                    Icon(
                        imageVector =
                        if (isFav.contains(id))
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,
                        tint =
                        if(isFav.contains(id))
                            Color.Red
                        else
                            MaterialTheme.colorScheme.onPrimary,
                        contentDescription =
                        if (isFav.contains(id))
                            "Supprimer des favoris"
                        else
                            "Ajouter aux favoris",
                        modifier = Modifier.size(48.dp),
                    )
                }
            }
        }
        item {
            Text(
                text = "Auteur : "+ BookRepository.bibliotheque[idd].author
            )
        }
        item {
            Image(
                painter = painterResource(id = BookRepository.bibliotheque[idd].image.mapToMyImageResource()),
                    contentDescription = "Couverture",
                    modifier = Modifier
                        .size(500.dp)
                )
        }
        item {
            Text(
                text = "Résumé : "+ BookRepository.bibliotheque[idd].plot
            )
        }
        item {
            Text(
                text = "Genre : "+ BookRepository.bibliotheque[idd].genre.toString()
            )
        }
        item {
            Text(
                text = "Année de sortie : "+ BookRepository.bibliotheque[idd].year.toString()
            )
        }
        item {
            Text(
                text = "Editeur : "+ BookRepository.bibliotheque[idd].editor
            )
        }
        item {
            Text(
                text = "Nombre de pages : "+ BookRepository.bibliotheque[idd].pages.toString()
            )
        }
        item {
            Row {
                Text(
                    text = "Etiquette : "+ BookRepository.bibliotheque[idd].etiquette.toString()
                )
                Card {  }
            }
        }
        item {

            ProgressInput(
                progressPages = BookRepository.bibliotheque[idd].progression.toString(),
                onValueChange = { pages ->
                    val pagesInt = pages.toIntOrNull() ?: 0
                    // Mise à jour de la progression du livre
                    BookRepository.updateBookProgress(
                        BookRepository.bibliotheque[idd].id.toString(), pagesInt
                    )
                },
                nbpages = BookRepository.bibliotheque[idd].pages.toString()
            )
        }
    }
}