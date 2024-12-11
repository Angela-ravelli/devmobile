package ufr.mim.devmobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ufr.mim.devmobile.R

@Composable
fun BooksList(nameList: String){
    LazyColumn {
        item(6){
            Row(){
                Image(
                    painter = painterResource(R.drawable.charlie),
                    contentDescription = R.drawable.charlie.toString()
                )
            }
        }
    }
}