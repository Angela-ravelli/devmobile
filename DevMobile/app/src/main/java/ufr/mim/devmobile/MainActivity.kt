package ufr.mim.devmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ufr.mim.devmobile.ui.theme.BookTheme
import ufr.mim.netfloux.navigation.BooksNavigationComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookTheme {
                BooksNavigationComponent()
            }
        }
    }
}