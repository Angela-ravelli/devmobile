package ufr.mim.devmobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.R
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.components.ListesMinimize
import ufr.mim.devmobile.components.StatsHomeCard
import ufr.mim.devmobile.ui.theme.MainPadding


@Composable
fun HomeScreen() {
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
                onValueChange = { value ->
                    searchValue = value
                }
            )
        }

        // Message de bienvenue
        item {
            val name = "Angéla"
            Text(
                modifier = Modifier.padding(MainPadding, 0.dp),
                text = "Bonjour $name ! ",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge
            )
        }

        // Cartes de statistiques et listes
        item { StatsHomeCard() }
        item { ListesMinimize("Ma bibliothèque") }
        item { ListesMinimize("Livres en cours") }
        item { ListesMinimize("Livres en attente") }
    }
}





