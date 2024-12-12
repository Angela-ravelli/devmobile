package ufr.mim.devmobile.components

import ufr.mim.devmobile.viewmodel.NavigationViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ufr.mim.devmobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    navController: NavHostController,
    navigationViewModel: NavigationViewModel
) {
    val isInDepthNavigation by navigationViewModel.isInDepthNavigation.collectAsState()

    TopAppBar(
        modifier = Modifier.height(80.dp),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isInDepthNavigation == true) {
                    IconButton(onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 8.dp)
                    )
                }
                Text(
                    text = "ChapterBox",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}