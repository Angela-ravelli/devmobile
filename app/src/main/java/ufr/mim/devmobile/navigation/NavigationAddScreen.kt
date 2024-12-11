package ufr.mim.devmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.screens.AddDetailsScreen
import ufr.mim.devmobile.screens.AddScreen
import ufr.mim.devmobile.screens.DetailsScreen

@Composable
fun NavigationAddScreen(
    progressViewModel: ProgressViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController
) {

    // val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AddScreens.AddScreen.route
    ) {
        composable(AddScreens.AddScreen.route) {
            AddScreen(
                onDetails = {
                    navController.navigate(AddScreens.AddDetailsScreen.route)
                },
                onViewDetails = {
                    navController.navigate(AddScreens.DetailsScreen.route)
                },
                onListDetails = {
                    navController.navigate(AddScreens.LibrairyScreen.route)
                }
            )
        }

        composable(AddScreens.AddDetailsScreen.route) {
            AddDetailsScreen(
                progressViewModel,
                onCancel = { navController.popBackStack() },
                onSave = { navController.navigate(AddScreens.DetailsScreen.route) }
            )
        }

        composable(AddScreens.DetailsScreen.route) {
            DetailsScreen(
                //id = "",
                progressViewModel = progressViewModel,
                favoriteViewModel = favoriteViewModel,
            )
        }
    }

}