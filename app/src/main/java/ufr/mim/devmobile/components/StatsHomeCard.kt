package ufr.mim.devmobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.R
import ufr.mim.devmobile.ui.theme.MainPadding

@Composable
fun StatsHomeCard() {
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        modifier = Modifier
            .padding(32.dp, MainPadding)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(MainPadding)
        ) {
            Text(
                text = "Statistiques",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Image(
                    painter = painterResource(id = R.drawable.stars),
                    contentDescription = "Icônes d'étoiles",
                    modifier = Modifier
                        .size(24.dp)
                )

                Text(
                    text = "Vous avez lu 2h aujourd'hui",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }


            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Image(
                    painter = painterResource(id = R.drawable.stars),
                    contentDescription = "Icônes d'étoiles",
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(
                    text = "Vous avez lu 80 livres cette année",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}