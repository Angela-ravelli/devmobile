package ufr.mim.devmobile.screens

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.components.ListesMinimize
import ufr.mim.devmobile.components.dropShadow
import ufr.mim.devmobile.ui.theme.MainPadding


@Composable
fun AddScreen(onDetails: (String) -> Unit) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val focusRequester = FocusRequester()

   Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(0.dp, MainPadding),
            verticalArrangement = Arrangement.spacedBy(MainPadding, alignment = Alignment.Top),
        ) {
        Row (
            modifier = Modifier.padding(0.dp, MainPadding),
            verticalAlignment = Alignment.CenterVertically
        ){

           var searchValue by remember { mutableStateOf(TextFieldValue("")) }

            Box(modifier = Modifier.weight(1f)) {
                BookSearchBar(
                    text = "Rechercher un livre..",
                    value = searchValue,
                    onValueChange = { value ->
                        searchValue = value
                    }
                )
            }

            Button(onClick = { searchValue },
                colors = ButtonColors(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.onPrimary,
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.2F),
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5F)),
                modifier = Modifier
                    .padding(end = MainPadding)
                    .dropShadow(RoundedCornerShape(4.dp))
                ,
                shape = RoundedCornerShape(4.dp),
            ){
                Text(
                    text = "Ajouter un livre\n manuellement",
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }

        ListesMinimize("Suggestions")
        ListesMinimize("Romance")
        ListesMinimize("Science-fiction")
   }
}

