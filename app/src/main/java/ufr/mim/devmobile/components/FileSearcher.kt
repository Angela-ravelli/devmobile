package ufr.mim.devmobile.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun fileSearcher(titre: String): Uri? {
    var imageUri: Uri? by remember { mutableStateOf(null) }

    // Lancer le sélecteur de fichiers
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Box(
        modifier = Modifier
            .size(500.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                imagePickerLauncher.launch("image/*") // Lance le sélecteur d'image
            }
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Couverture $titre",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(
                text = "Cliquez pour choisir une image",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    return imageUri
}

