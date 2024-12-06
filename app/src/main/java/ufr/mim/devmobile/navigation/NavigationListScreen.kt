package ufr.mim.devmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen

@Composable
fun NavigationListScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ListScreens.ListScreen.name) {
        composable(ListScreens.ListScreen.name) {
            AddScreen(onDetails = { id ->
                navController.navigate(AddScreens.DetailsScreen.name)
            })
        }

        composable(ListScreens.DetailsScreen.name) {
            DetailsScreen()
        }
    }
}