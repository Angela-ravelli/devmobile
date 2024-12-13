package ufr.mim.devmobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.mapper.mapToMyImageResource
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun BookInList(book: Books,
               favoriteViewModel: FavoriteViewModel,
               nameList: String?,
               onViewDetails: (String) -> Unit){
    val isFav by favoriteViewModel.favoriteBooks.collectAsState()
    val index = book.id.toString()

    Column(
        modifier = Modifier
            .padding(MainPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(isFav.contains(index) && nameList == "Livres Favoris"){
            IconButton(
                onClick = {
                    favoriteViewModel.toggleFavorite(index)
                }
            ) {
                Icon(imageVector = Icons.Filled.Favorite,
                    contentDescription = "Supprimer des favoris",
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        Image(
            painter = painterResource(book.image.mapToMyImageResource()),
            contentDescription = book.image,
            modifier = Modifier.clickable { onViewDetails(book.id.toString()) }
        )

        Text(
            text = book.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        PercentageProgressBar(
            0.toFloat(),
            nbpages = book.pages.toFloat(),
            big = false
        )
    }
}