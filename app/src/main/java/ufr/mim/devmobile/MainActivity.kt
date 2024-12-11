package ufr.mim.devmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ufr.mim.devmobile.components.ContentView
import ufr.mim.devmobile.data.BooksViewModel
import ufr.mim.devmobile.data.DataStoreManager
import ufr.mim.devmobile.data.FavoriteViewModel
import ufr.mim.devmobile.data.ProgressViewModel
import ufr.mim.devmobile.ui.theme.BookTheme

class MainActivity : ComponentActivity() {

    private val dataStoreManager by lazy { DataStoreManager(applicationContext) }

    // Initialisation des ViewModels
    private val progressViewModel by viewModels<ProgressViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProgressViewModel(dataStoreManager) as T
            }
        }
    }

    private val favoriteViewModel by viewModels<FavoriteViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavoriteViewModel(dataStoreManager) as T
            }
        }
    }

    /*private val bookViewModel by viewModels<BooksViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BooksViewModel(dataStoreManager) as T
            }
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookTheme {
                ContentView(false,
                    progressViewModel = progressViewModel,
                    favoriteViewModel = favoriteViewModel
                   // booksViewModel = bookViewModel
                    )
            }
        }
    }
}