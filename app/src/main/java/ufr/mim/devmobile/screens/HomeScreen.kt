package ufr.mim.devmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.components.ListesMinimize
import ufr.mim.devmobile.components.StatsHomeCard
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.viewmodel.UserViewModel
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.ui.theme.MainPadding


@Composable
fun HomeScreen(
    onViewDetails: (String) -> Unit,
    onListDetails: (String) -> Unit,
    userViewModel: UserViewModel
) {
    val userName by userViewModel.userName.collectAsState()

    // État de la pop-up
    var showDialog by remember { mutableStateOf(userName == null) }
    var inputName by remember { mutableStateOf("") }

    // Afficher la pop-up si le prénom n'a pas été enregistré
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    "Bienvenue !",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.primary)
                        .padding(16.dp)
                ) {
                    Text(
                        "Entrez votre prénom :",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    TextField(
                        value = inputName,
                        onValueChange = { inputName = it },
                        placeholder = { Text("Votre prénom", color = Color.Gray) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        userViewModel.saveUserName(inputName)
                        showDialog = false
                    },
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(0.5f),
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Valider", color = Color.Black)
                }
            },
            containerColor = MaterialTheme.colorScheme.primary
        )
    }

    /* *** TEST AJOUT LIVRE */
    val new = Books(21, "titre", 2024, emptyList(), "auteur", "plot", 23, 345, emptyList(), "editeur", "")
    BookRepository.bookList.add(new)


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(0.dp, MainPadding),
        verticalArrangement = Arrangement.spacedBy(MainPadding, alignment = Alignment.Top)
    ) {
        // Barre de recherche
        item {
            var searchValue by remember { mutableStateOf(TextFieldValue("")) }

            BookSearchBar(
                text = "Rechercher dans mes livres..",
                value = searchValue,
                onValueChange = { value -> searchValue = value }
            )
        }

        // Message de bienvenue avec le prénom enregistré
        item {
            Text(
                modifier = Modifier.padding(MainPadding, 0.dp),
                text = "Bonjour ${userName ?: "Invité"} !",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge
            )
        }

        // Cartes de statistiques et listes
        item { StatsHomeCard() }
        item { ListesMinimize("Ma bibliothèque", onViewDetails, onListDetails) }
        item { ListesMinimize("Livres en cours", onViewDetails, onListDetails) }
        item { ListesMinimize("Livres en attente", onViewDetails, onListDetails) }
    }
}






