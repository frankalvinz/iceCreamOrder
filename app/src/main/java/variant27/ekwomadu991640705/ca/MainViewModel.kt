package variant27.ekwomadu991640705.ca

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

    /**
     * ViewModel for managing the state of the Ice Cream Land app.
     *
     * The [MainViewModel] class is responsible for holding and managing the state of the application.
     * It stores user input, such as the user's name, ice cream price, total price, and whether the price should be rounded up for charity.
     * This ViewModel ensures that the app's state is preserved across configuration changes and can be accessed by the UI.
     *
     * @Author Frank Ekwomadu. C
     */
class MainViewModel : ViewModel() {

    // Private State variables
    private var _name by mutableStateOf("")
    private var _iceCreamPrice by mutableStateOf(0f)
    private var _totalPrice by mutableStateOf(0f)
    private var _roundUpPrice by mutableStateOf(false)

    // Getter methods
    val name: String
        get() = _name
    val iceCreamPrice: Float
        get() = _iceCreamPrice
    val totalPrice: Float
        get() = _totalPrice
    val roundUpPrice: Boolean
        get() = _roundUpPrice

    // Setter methods
    fun setName(newName: String) {
        _name = newName
    }
    fun setIceCreamPrice(newIceCreamPrice: Float) {
        _iceCreamPrice = newIceCreamPrice
    }
    fun setTotalPrice(newETotalPrice: Float) {
        _totalPrice = newETotalPrice
    }
    fun setRoundUpPrice(isRoundedUp: Boolean) {
        _roundUpPrice = isRoundedUp
    }
}
