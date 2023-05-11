package asia.fourtitude.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val refreshing = mutableStateOf(false)
    val errorMessage = mutableStateOf("")
    val responseCode = mutableStateOf(ResponseCode.UNKNOWN.code)

}

enum class ResponseCode(val code : Int) {
    UNKNOWN(0),
    UNAUTHORIZED(401),
    OK(200),
}

