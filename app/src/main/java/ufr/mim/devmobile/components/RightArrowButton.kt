package ufr.mim.devmobile.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.ui.theme.MainPadding


@Composable
fun RightArrowButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonColors(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.6F),
            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8F)
        ),
        modifier = Modifier
            .size(60.dp)
            .dropShadow(CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowForward, // Flèche vers la droite
            contentDescription = "Flèche droite",
            modifier = Modifier.size(24.dp)
        )
    }
}