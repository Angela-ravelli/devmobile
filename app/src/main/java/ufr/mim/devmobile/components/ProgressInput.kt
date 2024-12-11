package ufr.mim.devmobile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressInput(
    progressPages: String,
    onValueChange: (String) -> Unit,
    nbpages: String
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Progression : ")

            TextField(
                value = progressPages,
                onValueChange = onValueChange,
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp),
                placeholder = { Text("0") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6F),
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                )
            )

            Text(text = " / $nbpages")
        }
        val progress = progressPages.toFloatOrNull()?.coerceIn(0f, 100f)?.div(100f) ?: 0f
        /* changer 100 par nombre de pages */

        PercentageProgressBar(progress)
    }
}