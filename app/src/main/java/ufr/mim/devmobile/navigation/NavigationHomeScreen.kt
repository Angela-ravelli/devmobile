package ufr.mim.devmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen

@Composable
fun NavigationHomeScreen(viewModel: ProgressViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreens.HomeScreen.name) {
        composable(HomeScreens.HomeScreen.name) {
            AddScreen(onDetails = { id ->
                navController.navigate(HomeScreens.DetailsScreen.name)
            })
        }

        composable(HomeScreens.DetailsScreen.name) {
            DetailsScreen(viewModel)
        }
    }
}