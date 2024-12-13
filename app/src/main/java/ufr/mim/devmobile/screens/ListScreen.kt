package ufr.mim.devmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.components.ListesMinimize
import ufr.mim.devmobile.ui.theme.MainPadding
import ufr.mim.devmobile.viewmodel.FavoriteViewModel

@Composable
fun ListScreen(onViewDetails: (String) -> Unit, onListDetails: (String) -> Unit, favoriteViewModel: FavoriteViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(0.dp, MainPadding),
        verticalArrangement = Arrangement.spacedBy(MainPadding, alignment = Alignment.Top),
    ) {
        item {
            var searchValue by remember { mutableStateOf(TextFieldValue("")) }

            BookSearchBar(
                text = "Rechercher un livre..",
                value = searchValue,
                onValueChange = { value ->
                    searchValue = value
                }
            )
        }

        item {
            ListesMinimize("Livres Favoris", onViewDetails, onListDetails, favoriteViewModel)
            ListesMinimize("Livres à acheter", onViewDetails, onListDetails, favoriteViewModel)
            ListesMinimize("Livres déjà lus", onViewDetails, onListDetails, favoriteViewModel)
        }
    }
}