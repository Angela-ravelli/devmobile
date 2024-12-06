package ufr.mim.netfloux.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ufr.mim.devmobile.navigation.AddScreens
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen

@Composable
fun BooksNavigationComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AddScreens.AddScreen.name) {
        composable(AddScreens.AddScreen.name) {
            AddScreen(onDetails = { id ->
                navController.navigate(AddScreens.DetailsScreen.name)
            })
        }

        composable(AddScreens.DetailsScreen.name) {
            DetailsScreen()
        }
    }
}