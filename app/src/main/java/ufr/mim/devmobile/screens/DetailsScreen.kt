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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.R
import ufr.mim.devmobile.components.ProgressInput
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun DetailsScreen(
    //id: String?,
    progressViewModel: ProgressViewModel,
    favoriteViewModel: FavoriteViewModel,
) {
    val isFav by favoriteViewModel.favoriteBooks.collectAsState()

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
                    text = "Titre du livre",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        favoriteViewModel.toggleFavorite("id")
                    }
                ) {
                    Icon(
                        imageVector =
                        if (isFav.contains("id"))
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,
                        contentDescription =
                        if (isFav.contains("id"))
                            "Supprimer des favoris"
                        else
                            "Ajouter aux favoris",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
        item {
            Text(
                text = "Auteur"
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.charlie),
                contentDescription = "Couverture",
                modifier = Modifier
                    .size(500.dp)
            )
        }
        item {
            Text(
                text = "Résumé : "
            )
        }
        item {
            Text(
                text = "Genre : "
            )
        }
        item {
            Text(
                text = "Date de sortie : "
            )
        }
        item {
            Text(
                text = "Editeur : "
            )
        }
        item {
            Text(
                text = "Nombre de pages : "
            )
        }
        item {
            Row {
                Text(
                    text = "Etiquettes : "
                )
                Card {  }
            }
        }
        item {
            val progressPages by progressViewModel.progressPages.collectAsState()

            ProgressInput(
                progressPages = progressPages.toString(),
                onValueChange = { pages ->
                    val pagesInt = pages.toIntOrNull() ?: 0
                    progressViewModel.saveProgress(pagesInt)
                },
                nbpages = "Nombre de pages"
            )
        }
    }
}