package ufr.mim.devmobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ufr.mim.devmobile.components.BookInList
import ufr.mim.devmobile.components.listChoice
import ufr.mim.devmobile.viewmodel.FavoriteViewModel


@SuppressLint("MutableCollectionMutableState")
@Composable
fun LibrairyScreen(nameList: String,
                   onViewDetails: (String) -> Unit,
                   favoriteViewModel: FavoriteViewModel
) {
    val favorites by favoriteViewModel.favoriteBooks.collectAsState()

    val books = nameList.listChoice(favorites)

    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(text = nameList,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                ),
                color = MaterialTheme.colorScheme.onBackground)
        }

        items(books.size / 2 + books.size % 2) { rowIndex ->

            Row (
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Premier élément de la colonne
                val firstIndex = rowIndex * 2
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(300.dp)
                ) {
                    BookInList(
                        book = books[firstIndex],
                        favoriteViewModel = favoriteViewModel,
                        nameList = nameList,
                        onViewDetails = onViewDetails
                    )
                }

                // Deuxième élément de la colonne (s'il existe)
                if (firstIndex + 1 < books.size) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(300.dp)
                    ) {
                        BookInList(
                            book = books[firstIndex + 1],
                            favoriteViewModel = favoriteViewModel,
                            nameList = nameList,
                            onViewDetails = onViewDetails
                        )
                    }
                }
            }
        }
    }
}


