package ufr.mim.devmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.BookInList
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.components.ListesMinimize
import ufr.mim.devmobile.components.StatsHomeCard
import ufr.mim.devmobile.components.dropShadow
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.ui.theme.MainPadding
import ufr.mim.devmobile.viewmodel.FavoriteViewModel


@Composable
fun AddScreen(onDetails: (String) -> Unit, onViewDetails: (String) -> Unit,
              onListDetails: (String) -> Unit, favoriteViewModel: FavoriteViewModel) {

    var searchValue by remember { mutableStateOf(TextFieldValue("")) }
    val searchResults = BookRepository.bookList.filter { book ->
        book.title.contains(searchValue.text, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(0.dp, MainPadding),
        verticalArrangement = Arrangement.spacedBy(MainPadding, alignment = Alignment.Top)
    ) {
        // Barre de recherche et bouton "ajouter livre"
        item {
            Row(
                modifier = Modifier.padding(bottom = MainPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    BookSearchBar(
                        text = "Rechercher un livre..",
                        value = searchValue,
                        onValueChange = { value ->
                            searchValue = value
                        }
                    )
                }

                Button(
                    onClick = { onDetails("") },
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
                        text = "Ajouter un livre\n manuellement",
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )
                }
            }
        }

        if (searchValue.text.isNotEmpty()) {
            items(searchResults) { book ->
                BookInList(
                    book = book,
                    favoriteViewModel = favoriteViewModel,
                    nameList = "Recherche",
                    onViewDetails = onViewDetails
                )
            }
        } else {
            // Listes par genres
            item { ListesMinimize("Suggestions", onViewDetails, onListDetails, favoriteViewModel) }
            item { ListesMinimize("Romance", onViewDetails, onListDetails, favoriteViewModel) }
            item { ListesMinimize("Science-fiction", onViewDetails, onListDetails, favoriteViewModel) }
            item { ListesMinimize("Policier", onViewDetails, onListDetails, favoriteViewModel) }
        }

    }
}


