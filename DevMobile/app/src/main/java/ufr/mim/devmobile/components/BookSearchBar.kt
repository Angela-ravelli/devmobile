package ufr.mim.devmobile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import ufr.mim.devmobile.ui.theme.SearchPlaceholder

@Composable
fun BookSearchBar(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Rechercher un film") },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8F),
            unfocusedContainerColor = MaterialTheme.colorScheme.primary,
            focusedPlaceholderColor = SearchPlaceholder,
            unfocusedPlaceholderColor = SearchPlaceholder,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
        ),
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = Icons.Filled.Search.toString()) },
        trailingIcon = {
            if (value.text.isNotEmpty()) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = Icons.Filled.Close.toString(),
                    Modifier.clickable { onValueChange(TextFieldValue("")) })
            }

        }
    )
}
