package ufr.mim.devmobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ufr.mim.devmobile.R
import ufr.mim.devmobile.model.Books
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun BookInList(book: Books){
    Column(
        modifier = Modifier
            .padding(MainPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.charlie),
            contentDescription = R.drawable.charlie.toString()
        )

        Text(
            text = book.title
        )

        PercentageProgressBar(56.toFloat())
    }
}