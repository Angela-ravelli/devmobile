package ufr.mim.devmobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ufr.mim.devmobile.R
import ufr.mim.devmobile.viewmodel.UserViewModel
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun StatsScreen(userViewModel: UserViewModel){
    val userName by userViewModel.userName.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(MainPadding),
        verticalArrangement = Arrangement.spacedBy(MainPadding, alignment = Alignment.Top)
    ) {
        item {
            Text(
                text = "Bonjour $userName,",
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        item {
            Text(
                text = "Statistiques",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        item {
            Image(
                painter = painterResource(R.drawable.stat1),
                contentDescription = R.drawable.stat1.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
            )
        }

        item {
            Image(
                painter = painterResource(R.drawable.stat2),
                contentDescription = R.drawable.stat2.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
            )
        }

        item {
            Image(
                painter = painterResource(R.drawable.stat3),
                contentDescription = R.drawable.stat3.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
            )
        }
    }
}