package ufr.mim.devmobile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json
import ufr.mim.devmobile.R
import ufr.mim.devmobile.data.BookRepository
import ufr.mim.devmobile.data.bookData
import ufr.mim.devmobile.data.mapToMyImageResource
import ufr.mim.devmobile.mapper.BookMapper
import ufr.mim.devmobile.model.BookDto
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun ListesMinimize(nameList: String, onViewDetails: (String) -> Unit,
                   onListDetails: (String) -> Unit) {
    /*val bookListDto: BookDto = Json.decodeFromString(bookData)
    val bookMapper = BookMapper()
    val bookList: List<Books> = bookListDto.books.map { bookMapper.mapBookDtoToBook(it) }*/
    val bookList = BookRepository.bookList

    //val books by booksViewModel.allBooks.collectAsState()

    // Affiche le nom de la liste
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 4.dp)
    ){
        Text(
            modifier = Modifier
                .padding(start = MainPadding),
            text = nameList
        )

        // Affiche les couvertures de la liste
        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(6) { index ->
                val startPadding = if(index == 0) MainPadding else 0.dp
                //Log.d("DEBBUG", books.toString())
                Image(
                    painter = painterResource(id = bookList[index].image.mapToMyImageResource()),
                    contentDescription = bookList[index].image,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = startPadding, end = MainPadding)
                        .clickable { onViewDetails(bookList[index].id.toString()) }
                )
            }

            item {
                RightArrowButton { onListDetails(nameList) }
            }
        }
    }
}

