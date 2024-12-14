package ufr.mim.devmobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.mapper.mapToMyImageResource
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun BookInList(
    book: Books,
    favoriteViewModel: FavoriteViewModel,
    nameList: String,
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
                    tint = Color.Red,
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

        val nbpagesFloat = book.pages.toString().toFloatOrNull() ?: 1f
        val progress = book.progression.toString()
            .toFloatOrNull()?.coerceIn(0f, nbpagesFloat)?.div(nbpagesFloat) ?: 0f


        if(nameList != "Suggestions" && nameList != "Romance" &&
            nameList != "Science-fiction" && nameList != "Policier") {
            if(nameList == "Ma bibliothèque") {
                Button(
                    onClick = { BookRepository.bibliotheque.remove(book) },
                    shape = CircleShape,
                    colors = ButtonColors(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.onPrimary,
                        Color.Gray.copy(alpha = 0.6F),
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8F)
                    ),
                    enabled = BookRepository.bibliotheque.contains(book),
                    modifier = Modifier
                        .dropShadow(shape = CircleShape)
                        .size(30.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = Icons.Default.Clear.toString(),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            PercentageProgressBar(
                progress,
                big = false
            )
        }
        else {
            Button(
                onClick = { BookRepository.bibliotheque.add(book) },
                shape = CircleShape,
                colors = ButtonColors(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.onPrimary,
                    Color.Gray.copy(alpha = 0.6F),
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8F)
                ),
                enabled = !BookRepository.bibliotheque.contains(book),
                modifier = Modifier
                    .dropShadow(shape = CircleShape)
                    .size(30.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = Icons.Default.Add.toString(),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}