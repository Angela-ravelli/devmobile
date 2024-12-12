package ufr.mim.devmobile.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import ufr.mim.devmobile.components.BookInList
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.viewmodel.FavoriteViewModel

@Composable
fun LibrairyScreen(nameList: String?,
                   onViewDetails: (String) -> Unit,
                   favoriteViewModel: FavoriteViewModel
) {

    val bookList = BookRepository.bookList

    LazyColumn {
        items(bookList.count()) { index ->
            Row {
                BookInList(bookList[index], favoriteViewModel, nameList, onViewDetails)
                BookInList(bookList[index+2], favoriteViewModel, nameList, onViewDetails)
            }
        }
    }
}