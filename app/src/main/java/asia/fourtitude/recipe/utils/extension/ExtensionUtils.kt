package asia.fourtitude.recipe.utils.extension

import android.app.Activity
import android.content.res.Resources
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.*

// LOGGER
fun Any?.printToLog(tag: String = "DEBUG_LOG") {
  Log.d(tag, toString())
}

// TOAST
fun Activity.toast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.toast(@StringRes message: Int) {
  Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// KEYBOARD
fun Activity.hideKeyboard() {
  val imm: InputMethodManager =
    getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  val view = currentFocus ?: View(this)
  imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

// PX-DP CONVERSION
val Int.px: Int
  get() = (this * Resources.getSystem().displayMetrics.density).toInt()

// DIGIT, ALPHABETIC, AND ALPHANUMERIC CHECK
val String.isDigitOnly: Boolean
  get() = matches(Regex("^\\d*\$"))

val String.isAlphabeticOnly: Boolean
  get() = matches(Regex("^[a-zA-Z]*\$"))

val String.isAlphanumericOnly: Boolean
  get() = matches(Regex("^[a-zA-Z\\d]*\$"))

// DATE FORMATTER
fun String.toDate(format: String = "yyyy-MM-dd HH:mm:ss"): Date? {
  val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
  return dateFormatter.parse(this)
}

fun Date.toDateStringFormat(format: String = "yyyy-MM-dd HH:mm:ss"): String {
  val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
  return dateFormatter.format(this)
}

fun Date.toTransactionDateStringFormat(format: String = "dd MMMM yyyy | HH:mm"): String {
  val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
  return dateFormatter.format(this)
}

fun Date.toMonthYearLabel(format: String = "MMM yyyy"): String {
  val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
  return dateFormatter.format(this)
}