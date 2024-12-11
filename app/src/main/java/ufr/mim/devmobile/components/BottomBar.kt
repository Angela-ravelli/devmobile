package ufr.mim.devmobile.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

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