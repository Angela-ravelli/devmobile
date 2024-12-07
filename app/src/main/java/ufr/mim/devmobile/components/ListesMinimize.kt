package ufr.mim.devmobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.w3c.dom.NameList
import ufr.mim.devmobile.R

@Composable
fun ListesMinimize(nameList: String) {
    // Affiche le nom de la liste
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
    ){
        Text(nameList)

        // Affiche les couvertures de la liste
        Row (
            modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.charlie),
                contentDescription = "Couverture",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp, 0.dp)
            )
        }
    }
}