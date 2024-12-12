import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel : ViewModel() {
    private val _isInDepthNavigation = MutableStateFlow(false)
    val isInDepthNavigation: StateFlow<Boolean> = _isInDepthNavigation

    fun setInDepthNavigation(inDepth: Boolean) {
        _isInDepthNavigation.value = inDepth
    }
}