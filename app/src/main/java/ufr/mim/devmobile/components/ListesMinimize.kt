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
                    contentDescription = "Couverture",
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

@DrawableRes
fun String.mapToMyImageResource() : Int =
    when(this) {
        "charlie" -> { R.drawable.charlie }
        "la_face_cachee_de_margo" -> { R.drawable.la_face_cachee_de_margo }
        "le_theoreme_des_katherines" -> { R.drawable.le_theoreme_des_katherines }
        "harry_potter_tome_1" -> { R.drawable.harry_potter_tome_1 }
        "harry_potter_tome_2" -> { R.drawable.harry_potter_tome_2}
        "harry_potter_tome_3" -> { R.drawable.harry_potter_tome_3 }
        "harry_potter_tome_4" -> { R.drawable.harry_potter_tome_4 }
        "harry_potter_tome_5" -> { R.drawable.harry_potter_tome_5 }
        "harry_potter_tome_6" -> { R.drawable.harry_potter_tome_6 }
        "harry_potter_tome_7" -> { R.drawable.harry_potter_tome_7 }
        "le_cri" -> { R.drawable.le_cri }
        "l_ile_du_diable" -> { R.drawable.l_ile_du_diable }
        "noa_torson_tome_1" -> { R.drawable.noa_torson_tome_1 }
        "noa_torson_tome_2" -> { R.drawable.noa_torson_tome_2 }
        "noa_torson_tome_3" -> { R.drawable.noa_torson_tome_3 }
        "lait_et_miel" -> { R.drawable.lait_et_miel }
        "danser_sous_la_pluie" -> { R.drawable.danser_sous_la_pluie }
        "ma_maison_en_fleur" -> { R.drawable.ma_maison_en_fleur }
        "qui_es_tu_alaska" -> { R.drawable.qui_es_tu_alaska }
        "complot" -> { R.drawable.complot }
        else -> {
            -1
        }
    }