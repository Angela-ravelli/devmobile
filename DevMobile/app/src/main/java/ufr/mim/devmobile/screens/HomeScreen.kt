package ufr.mim.devmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
//import kotlinx.serialization.json.Json
//import ufr.mim.devmobile.components.MovieCard
import ufr.mim.devmobile.components.BookSearchBar
//import ufr.mim.devmobile.components.RawButton
import ufr.mim.devmobile.data.bookData
import ufr.mim.devmobile.mapper.BookMapper
//import ufr.mim.devmobile.model.Book
//import ufr.mim.devmobile.model.BookDto
//import ufr.mim.devmobile.navigation.MoviesScreens
import ufr.mim.devmobile.ui.theme.MainPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onDetails: (String) -> Unit) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ChapterBox",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        modifier = Modifier.pointerInput(Unit) { detectTapGestures(onTap = {
            focusManager.clearFocus()
        }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(MainPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
        ) {
            Text(
                text = "Bonjour ! ",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge
            )

            var searchValue by remember { mutableStateOf(TextFieldValue("")) }
            BookSearchBar(
                value = searchValue,
                onValueChange = { value ->
                    searchValue = value
                }
            )

            //val movieListDto: MovieDto = Json.decodeFromString(movieData)
            //val movieMapper = MovieMapper()
            //val movieList: List<Movie> = movieListDto.movies.map { movieMapper.mapMovieDtoToMovie(it) }
            //val filteredList: List<Movie> = movieList.filter { it.title.lowercase().contains(searchValue.text.lowercase()) }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(MainPadding)
            ) {
                /*items(filteredList) { movie ->
                    RawButton(onClick = { onDetails(movie.id.toString()) }) {
                        MovieCard(movie)
                    }
                }*/
            }
        }
    }
}