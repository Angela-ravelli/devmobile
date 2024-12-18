package ufr.mim.devmobile.components

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.mapper.mapToMyImageResource
import ufr.mim.devmobile.ui.theme.MainPadding
import ufr.mim.devmobile.viewmodel.FavoriteViewModel

@Composable
fun ListesMinimize(nameList: String, onViewDetails: (String) -> Unit,
                   onListDetails: (String) -> Unit, favoriteViewModel: FavoriteViewModel) {
    val favorites by favoriteViewModel.favoriteBooks.collectAsState()

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
            val i : Int
            if(nameList.listChoice(favorites).size>=6){ i = 6 }
            else { i = nameList.listChoice(favorites).size }

            items(i) { index ->
                val startPadding = if(index == 0) MainPadding else 0.dp
                Image(
                    painter = painterResource(id = nameList.listChoice(favorites)[index].image.mapToMyImageResource()),
                    contentDescription = nameList.listChoice(favorites)[index].image,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = startPadding, end = MainPadding)
                        .clickable {
                            onViewDetails(nameList.listChoice(favorites)[index].id.toString()) }
                )
            }

            item {
                RightArrowButton { onListDetails(nameList) }
            }
        }
    }
}