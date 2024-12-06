package ufr.mim.devmobile.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.R
import ufr.mim.devmobile.screens.HomeScreen
import ufr.mim.devmobile.screens.ListScreen
import ufr.mim.devmobile.ui.theme.MainPadding
import ufr.mim.netfloux.navigation.BooksNavigationComponent

data class MyIcon(
    val index: Int,
    val action: () -> Unit,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BottomBar(selectedTab : Int, icons : List<MyIcon>) {

    BottomAppBar(modifier = Modifier.height(80.dp),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        icons.forEach { icon ->
            IconButton(onClick = icon.action, modifier = Modifier.weight(1f)) {
                val iconDrawable =
                    if (selectedTab == icon.index) icon.selectedIcon else icon.unselectedIcon
                Icon(iconDrawable, iconDrawable.name)
            }
        }
    }
}