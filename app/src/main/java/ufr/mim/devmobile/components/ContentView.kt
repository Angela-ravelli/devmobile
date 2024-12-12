package ufr.mim.devmobile.components

import NavigationViewModel
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.rememberNavController
import ufr.mim.devmobile.R
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.viewmodel.UserViewModel
import ufr.mim.devmobile.navigation.NavigationAddScreen
import ufr.mim.devmobile.navigation.NavigationHomeScreen
import ufr.mim.devmobile.navigation.NavigationListScreen
import ufr.mim.devmobile.screens.StatsScreen

@Composable
fun ContentView(mainBar: Boolean,
                favoriteViewModel: FavoriteViewModel,
                userViewModel: UserViewModel
                ) {

    var selectedTab by remember { mutableIntStateOf(0) }

    val homeFilled = ImageVector.vectorResource(id = R.drawable.home_filled)
    val homeOutlined = ImageVector.vectorResource(id = R.drawable.home_outlined)
    val addFilled = ImageVector.vectorResource(id = R.drawable.add_filled)
    val addOutlined = ImageVector.vectorResource(id = R.drawable.add_outlined)
    val bookmarksFilled = ImageVector.vectorResource(id = R.drawable.bookmarks_filled)
    val bookmarksOutlined = ImageVector.vectorResource(id = R.drawable.bookmarks_outlined)
    val analyticsFilled = ImageVector.vectorResource(id = R.drawable.analytics_filled)
    val analyticsOutlined = ImageVector.vectorResource(id = R.drawable.analytics_outlined)

    val icons: List<MyIcon> = listOf(
        MyIcon(0, { selectedTab = 0 }, homeFilled, homeOutlined),
        MyIcon(1, { selectedTab = 1 }, addFilled, addOutlined),
        MyIcon(2, { selectedTab = 2 }, bookmarksFilled, bookmarksOutlined),
        MyIcon(3, { selectedTab = 3 }, analyticsFilled, analyticsOutlined)
    )

    val addScreenNavController = rememberNavController()
    val navigationViewModel = remember { NavigationViewModel() } // Instance du ViewModel partagé


    val homeNavController = rememberNavController()
    val addNavController = rememberNavController()
    val listNavController = rememberNavController()

    // Obtenir le bon NavController actif
    val currentNavController = when (selectedTab) {
        0 -> homeNavController
        1 -> addNavController
        2 -> listNavController
        else -> homeNavController // Par défaut, ou un autre si nécessaire
    }

    Scaffold(
        topBar = { MyTopBar(mainBar, currentNavController, navigationViewModel) },
        bottomBar = { BottomBar(selectedTab, icons) }
    ) { paddingValues ->
        // Section centrale change en fonction de l'onglet
        Crossfade(
            targetState = selectedTab,
            animationSpec = tween(500),
            label = "Crossfade tabs",
            modifier = Modifier.padding(paddingValues)
        ) { tabIndex ->
            when (tabIndex) {
                0 -> {
                    NavigationHomeScreen(
                        favoriteViewModel,
                        userViewModel,
                        navigationViewModel
                    )
                }
                1 -> {
                    NavigationAddScreen(
                        favoriteViewModel,
                        navigationViewModel
                    )
                }

                2 -> {
                    NavigationListScreen(
                        favoriteViewModel,
                        navigationViewModel
                    )
                }
                3 -> {
                    StatsScreen(userViewModel)
                }
            }
        }
    }
}