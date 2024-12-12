package ufr.mim.devmobile.components

import android.util.Log
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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ufr.mim.devmobile.R
import ufr.mim.devmobile.data.BookRepository
import ufr.mim.devmobile.data.bookData
import ufr.mim.devmobile.data.mapToMyImageResource
import ufr.mim.devmobile.mapper.BookMapper
import ufr.mim.devmobile.model.BookDto
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.ui.theme.MainPadding
import java.io.File

//var bookList : MutableList<Books> = BookRepository.bookList.toMutableList()

@Composable
fun ListesMinimize(nameList: String, onViewDetails: (String) -> Unit,
                   onListDetails: (String) -> Unit) {
    /*val bookListDto: BookDto = Json.decodeFromString(bookData)
    val bookMapper = BookMapper()
    val bookList: List<Books> = bookListDto.books.map { bookMapper.mapBookDtoToBook(it) }*/

    //val bookData: String = encodeBooksToJson(bookList)
    //val filePath = "C:\\Users\\ravel\\Desktop\\devmobile\\app\\src\\main\\java\\ufr\\mim\\devmobile\\data\\Data.kt"
    //writeJsonToFile(filePath, bookData)
    //updateFile(filePath, bookData)

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
                Image(
                    painter = painterResource(id = BookRepository.bookList[index].image.mapToMyImageResource()),
                    contentDescription = BookRepository.bookList[index].image,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = startPadding, end = MainPadding)
                        .clickable {
                            Log.d("ID Liste ", BookRepository.bookList[index].id.toString())
                            onViewDetails(BookRepository.bookList[index].id.toString()) }
                )
            }

            item {
                RightArrowButton { onListDetails(nameList) }
            }
        }
    }
}
