package ufr.mim.devmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.ui.theme.MainPadding


@Composable
fun AddScreen(onDetails: (String) -> Unit) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val focusRequester = FocusRequester()

   Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(MainPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
        ) {
        Row() {

           var searchValue by remember { mutableStateOf(TextFieldValue("")) }

           BookSearchBar(
               value = searchValue,
               onValueChange = { value ->
                   searchValue = value
               }
           )
            Button(onClick = { searchValue },
                colors = ButtonColors(MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.onPrimary,
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.2F),
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5F))
            ){

            }
        }
   }
}

