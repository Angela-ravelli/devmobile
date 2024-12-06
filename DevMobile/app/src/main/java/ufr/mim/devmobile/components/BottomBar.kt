package ufr.mim.devmobile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.R

data class MyIcon(
    val index: Int,
    val action: () -> Unit,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val homeFilled = ImageVector.vectorResource(id = R.drawable.home_filled)
    val homeOutlined = ImageVector.vectorResource(id = R.drawable.home_outlined)
    val addFilled = ImageVector.vectorResource(id = R.drawable.add_filled)
    val addOutlined = ImageVector.vectorResource(id = R.drawable.add_outlined)
    val bookmarksFilled = ImageVector.vectorResource(id = R.drawable.bookmarks_filled)
    val bookmarksOutlined = ImageVector.vectorResource(id = R.drawable.bookmarks_outlined)
    val laptopMetrics = ImageVector.vectorResource(id = R.drawable.laptop_metrics)

    val icons: List<MyIcon> = listOf(
        MyIcon(0, { onTabSelected(0) }, homeFilled, homeOutlined),
        MyIcon(1, { onTabSelected(1) }, addFilled, addOutlined),
        MyIcon(2, { onTabSelected(2) }, bookmarksFilled, bookmarksOutlined),
        MyIcon(3, { onTabSelected(3) }, laptopMetrics, laptopMetrics)
    )

    BottomAppBar(modifier = modifier.height(80.dp)) {
        icons.forEach { icon ->
            IconButton(onClick = icon.action, modifier = Modifier.weight(1f)) {
                val iconDrawable =
                    if (selectedTab == icon.index) icon.selectedIcon else icon.unselectedIcon
                Icon(iconDrawable, iconDrawable.name)
            }
        }
    }
}



@Composable
fun MyHome(modifier: Modifier) {
    Column(modifier = modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home")
    }
}

@Composable
fun AddPage(modifier: Modifier) {
    Column(modifier = modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("AddPage")
    }
}