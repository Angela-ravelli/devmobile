package ufr.mim.devmobile.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.components.BottomBar
import ufr.mim.devmobile.components.MyIcon
import ufr.mim.devmobile.components.MyTopBar
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
            var searchValue by remember { mutableStateOf(TextFieldValue("")) }

            BookSearchBar(
                value = searchValue,
                onValueChange = { value ->
                    searchValue = value
                }
            )
        }
    }

