package ufr.mim.devmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ufr.mim.devmobile.components.ContentView
import ufr.mim.devmobile.viewmodel.DataStoreManager
import ufr.mim.devmobile.viewmodel.FavoriteViewModel
import ufr.mim.devmobile.viewmodel.UserViewModel
import ufr.mim.devmobile.ui.theme.BookTheme
import ufr.mim.devmobile.viewmodel.NavigationViewModel

class MainActivity : ComponentActivity() {

    private val dataStoreManager by lazy { DataStoreManager(applicationContext) }

    private val favoriteViewModel by viewModels<FavoriteViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavoriteViewModel(dataStoreManager) as T
            }
        }
    }

    private val userViewModel by viewModels<UserViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(dataStoreManager) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookTheme {
                ContentView(
                    favoriteViewModel = favoriteViewModel,
                    userViewModel = userViewModel,
                    navigationViewModel = NavigationViewModel()
                )
            }
        }
    }
}