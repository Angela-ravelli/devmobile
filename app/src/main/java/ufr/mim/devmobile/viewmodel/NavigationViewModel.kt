package ufr.mim.devmobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel() : ViewModel() {
    private val _isInDepthNavigation = MutableStateFlow(false)
    val isInDepthNavigation: StateFlow<Boolean> = _isInDepthNavigation

    var navController : NavHostController?= null

    fun setInDepthNavigation(inDepth: Boolean) {
        _isInDepthNavigation.value = inDepth
    }
}
