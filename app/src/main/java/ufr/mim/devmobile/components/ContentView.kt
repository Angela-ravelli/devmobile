package ufr.mim.devmobile.components

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
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.data.UserViewModel
import ufr.mim.devmobile.navigation.NavigationAddScreen
import ufr.mim.devmobile.navigation.NavigationHomeScreen
import ufr.mim.devmobile.navigation.NavigationListScreen
import ufr.mim.devmobile.screens.StatsScreen

@Composable
fun ContentView(mainBar: Boolean,
                progressViewModel: ProgressViewModel,
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


    Scaffold(
        topBar = { MyTopBar(mainBar, addScreenNavController) },
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
                        progressViewModel,
                        favoriteViewModel,
                        userViewModel
                    )
                    //AddDetailsScreen(progressViewModel, favoriteViewModel, { }, { })
                }
                1 -> {
                    NavigationAddScreen(
                        progressViewModel,
                        favoriteViewModel,
                        addScreenNavController
                    )
                }

                2 -> {
                    NavigationListScreen(
                        progressViewModel,
                        favoriteViewModel
                    )
                }
                3 -> {
                    StatsScreen(userViewModel)
                }
            }
        }
    }
}