package ufr.mim.devmobile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import ufr.mim.devmobile.ui.theme.MainPadding


@Composable
fun PercentageProgressBar(progress: Float, nbpages: Float) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MainPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(MainPadding)
                )
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(MainPadding)
                )
                .padding(2.dp)
                .weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = progress)
                    .background(
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(MainPadding)
                    )
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "${(progress * nbpages).toInt()}%",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
