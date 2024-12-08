package ufr.mim.devmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

@Composable
fun ListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(0.dp, MainPadding),
        verticalArrangement = Arrangement.spacedBy(MainPadding, alignment = Alignment.Top),
    ) {
        var searchValue by remember { mutableStateOf(TextFieldValue("")) }

        BookSearchBar(
            text = "Rechercher un livre..",
            value = searchValue,
            onValueChange = { value ->
                searchValue = value
            }
        )

        ListesMinimize("Livres Favoris")
        ListesMinimize("Livres à acheter")
        ListesMinimize(" Livres déjà lus")
    }
}