package ufr.mim.devmobile.components

import android.util.Log
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
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.toList
import ufr.mim.devmobile.mapper.BookRepository
import ufr.mim.devmobile.data.DataList
import ufr.mim.devmobile.mapper.mapToMyImageResource
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.ui.theme.MainPadding
import ufr.mim.devmobile.viewmodel.BookViewModel

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
                    painter = painterResource(id = nameList.listChoice()[index].image.mapToMyImageResource()),
                    contentDescription = nameList.listChoice()[index].image,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = startPadding, end = MainPadding)
                        .clickable {
                            Log.d("ID Liste ", nameList.listChoice()[index].id.toString())
                            onViewDetails(nameList.listChoice()[index].id.toString()) }
                )
            }

            item {
                RightArrowButton { onListDetails(nameList) }
            }
        }
    }
}

fun String.listChoice() : MutableList<Books> {
    var listReturn = mutableListOf<Books>()
    var listInt = mutableListOf<Int>()
    when (this) {
        "Ma bibliothèque" -> { listReturn = BookRepository.bookList }
        "Livres en cours" -> { listInt = DataList.listEnCours }
        "Livres en attente" -> { listInt = DataList.listAttente }
        "Suggestions" -> { listInt = DataList.suggestion }
        "Romance" -> { listInt = DataList.romance }
        "Science-fiction" -> { listInt = DataList.science }
        "Policier" -> { listInt = DataList.policier }
        "Livres Favoris" -> { listInt = DataList.listFavoris }
        "Livres à acheter" -> { listInt = DataList.listAcheter }
        "Livres déjà lus" -> { listInt = DataList.listLu }
        else -> { listReturn = BookRepository.bookList }
    }
    if (listReturn.isEmpty()) {
        BookViewModel.initializeBooks()
        BookViewModel.bookList.value.forEach { book ->
            if (book.id in listInt) {
                listReturn.add(book)
            }
        }
    }

    return listReturn
}
