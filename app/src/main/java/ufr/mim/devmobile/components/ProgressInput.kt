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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp

@Composable
fun ProgressInput(
    progressPages: String,
    onValueChange: (String) -> Unit,
    nbpages: String
) {
    var textFieldValue by remember { mutableStateOf(progressPages) }

    // Cette fonction sera appelée lorsque le TextField reçoit le focus
    fun onFocusChange(isFocused: Boolean) {
        if (isFocused) {
            // Réinitialiser la valeur lorsque le champ est cliqué (focus)
            textFieldValue = ""
        }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Progression : ")

            TextField(
                value = textFieldValue,
                onValueChange = { newText ->
                    // Assurez-vous que la nouvelle valeur est un nombre ou vide
                    textFieldValue = newText
                    onValueChange(newText) // Transmettre la nouvelle valeur à l'extérieur
                },
                modifier = Modifier
                    .width(60.dp)
                    .height(50.dp)
                    .onFocusChanged { focusState ->
                        // Réinitialiser le champ quand il est cliqué
                        onFocusChange(focusState.isFocused)
                    },
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

        val nbpagesFloat = nbpages.toFloatOrNull() ?: 1f
        val progress =
            textFieldValue.toFloatOrNull()?.coerceIn(0f, nbpagesFloat)?.div(nbpagesFloat) ?: 0f

        PercentageProgressBar(progress, nbpagesFloat)
    }
}
