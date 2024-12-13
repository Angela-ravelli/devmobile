package ufr.mim.devmobile.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.ProgressInput
import ufr.mim.devmobile.components.fileSearcher
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun AddDetailsScreen(
    onCancel: () -> Unit, onAdd: () -> Unit
) {
    var bookTitle by remember { mutableStateOf("Titre du livre") }
    var author by remember { mutableStateOf("Auteur") }
    var summary by remember { mutableStateOf("Résumé") }
    var genre by remember { mutableStateOf("Genre") }
    var releaseDate by remember { mutableStateOf("Date de sortie") }
    var publisher by remember { mutableStateOf("Editeur") }
    var pageCount by remember { mutableStateOf("Nombre de pages") }
    var progression by remember { mutableStateOf("0") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

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
            imageUri = fileSearcher(bookTitle)
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
            ProgressInput(
                progressPages = progression,
                onValueChange = { pages ->
                    val pagesInt = pages.toIntOrNull() ?: 0
                    // Mise à jour de la progression du livre
                    BookRepository.updateBookProgress(
                        progression, pagesInt
                    )
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
                    onClick = {
                        BookRepository.bibliotheque.add(Books(
                            BookRepository.bibliotheque.last().id+1,
                            bookTitle,
                            releaseDate.toInt(),
                            genre.split(","),
                            author,
                            summary,
                            0,
                            pageCount.toInt(),
                            emptyList(),
                            publisher,
                            ""
                        ));
                        onAdd()
                    },
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

