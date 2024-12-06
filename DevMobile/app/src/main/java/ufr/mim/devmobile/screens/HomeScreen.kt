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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ufr.mim.devmobile.R
//import kotlinx.serialization.json.Json
//import ufr.mim.devmobile.components.MovieCard
import ufr.mim.devmobile.components.BookSearchBar
import ufr.mim.devmobile.components.BottomBar
import ufr.mim.devmobile.components.MyIcon
import ufr.mim.devmobile.components.MyTopBar
//import ufr.mim.devmobile.components.RawButton
import ufr.mim.devmobile.data.bookData
import ufr.mim.devmobile.mapper.BookMapper
//import ufr.mim.devmobile.model.Book
//import ufr.mim.devmobile.model.BookDto
//import ufr.mim.devmobile.navigation.MoviesScreens
import ufr.mim.devmobile.ui.theme.MainPadding


@Composable
fun HomeScreen() {

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

            val name = "Angéla"
            Text(
                text = "Bonjour $name ! ",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge
            )


        }
    }

