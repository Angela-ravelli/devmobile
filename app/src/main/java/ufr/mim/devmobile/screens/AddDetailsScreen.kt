package ufr.mim.devmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ufr.mim.devmobile.components.ProgressInput
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun AddDetailsScreen(
    progressViewModel: ProgressViewModel,
    favoriteViewModel: FavoriteViewModel,
    onCancel: () -> Unit,
    onSave: () -> Unit
) {
    val isFav by favoriteViewModel.favoriteBooks.collectAsState()
    var bookTitle by remember { mutableStateOf("Titre du livre") }
    var author by remember { mutableStateOf("Auteur") }
    var summary by remember { mutableStateOf("Résumé") }
    var genre by remember { mutableStateOf("Genre") }
    var releaseDate by remember { mutableStateOf("Date de sortie") }
    var publisher by remember { mutableStateOf("Editeur") }
    var pageCount by remember { mutableStateOf("Nombre de pages") }
    var imageUri by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier.padding(MainPadding),
        verticalArrangement = Arrangement.spacedBy(MainPadding)
    ) {
        // Titre & Favori
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = bookTitle,
                    onValueChange = { bookTitle = it },
                    label = { Text("Titre du livre") },
                    modifier = Modifier.weight(1f)
                )

                /*IconButton(
                    onClick = {
                        favoriteViewModel.toggleFavorite("id")
                    }
                ) {
                    Icon(
                        imageVector =
                        if (isFav.contains("id")) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription =
                        if (isFav.contains("id")) "Supprimer des favoris" else "Ajouter aux favoris",
                        modifier = Modifier.size(48.dp)
                    )
                }*/
            }
        }

        // Auteur
        item {
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Auteur") }
            )
        }

        // Image - Choix d'image
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { /* Lancer le sélecteur d'image */ }
            ) {
                if (imageUri != null) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "Couverture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = "Cliquez pour choisir une image",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        // Autres Champs
        item {
            OutlinedTextField(
                value = summary,
                onValueChange = { summary = it },
                label = { Text("Résumé") }
            )
        }
        item {
            OutlinedTextField(
                value = genre,
                onValueChange = { genre = it },
                label = { Text("Genre") }
            )
        }
        item {
            OutlinedTextField(
                value = releaseDate,
                onValueChange = { releaseDate = it },
                label = { Text("Date de sortie") }
            )
        }
        item {
            OutlinedTextField(
                value = publisher,
                onValueChange = { publisher = it },
                label = { Text("Editeur") }
            )
        }
        item {
            OutlinedTextField(
                value = pageCount,
                onValueChange = { pageCount = it },
                label = { Text("Nombre de pages") }
            )
        }

        // Progression
        item {
            val progressPages by progressViewModel.progressPages.collectAsState()

            ProgressInput(
                progressPages = progressPages.toString(),
                onValueChange = { pages ->
                    val pagesInt = pages.toIntOrNull() ?: 0
                    progressViewModel.saveProgress(pagesInt)
                },
                nbpages = pageCount
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Annuler")
                }

                Button(
                    onClick = onSave,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text("Valider")
                }
            }
        }
    }
}

