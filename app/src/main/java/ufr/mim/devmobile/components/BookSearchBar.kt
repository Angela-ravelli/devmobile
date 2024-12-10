package ufr.mim.devmobile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import ufr.mim.devmobile.ui.theme.MainBackgroundProgress
import ufr.mim.devmobile.ui.theme.MainPadding
import ufr.mim.devmobile.ui.theme.SearchPlaceholder

@Composable
fun BookSearchBar(text: String, value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {


    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MainPadding, 0.dp),
        placeholder = { Text(text) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6F),
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
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
        trailingIcon = {
            Row {
                if (value.text.isNotEmpty()) {
                    Icon(imageVector = Icons.Filled.Close,
                        contentDescription = Icons.Filled.Close.toString(),
                        Modifier.clickable { onValueChange(TextFieldValue("")) })
                }
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = Icons.Filled.Search.toString()
                )
            }
        }
    )
}
