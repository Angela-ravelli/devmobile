package ufr.mim.devmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.components.dropShadow
import ufr.mim.devmobile.ui.theme.MainPadding
import ufr.mim.devmobile.model.Books

@Composable
fun AddBookScreen() {
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(MainPadding, alignment = Alignment.Top)
    ) {
        item {
            Text(
                text = "Ici y'aura l'image mais pour l'instant on s'en branle je fais juste des tests",
            )
        }
        item {
            Text(
                text = "Titre",
            )
        }
        item {

            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Titre") }
            )
        }
        item {
            Text(
                text = "Auteur",
            )
        }
        item {
            TextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Auteur") }
            )
        }
        item {
            Button(
                onClick = {

                },
                colors = ButtonColors(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.onPrimary,
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.6F),
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8F)
                ),
                modifier = Modifier
                    .padding(end = MainPadding)
                    .dropShadow(RoundedCornerShape(4.dp)),
                shape = RoundedCornerShape(4.dp),
            ) {
                Text(
                    text = "Ajouter",
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }
    }

}