package asia.fourtitude.recipe.utils.common

import android.content.Context
import android.location.Geocoder
import androidx.activity.ComponentActivity
import java.text.DecimalFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.Duration

fun formatTime(seconds: String, minutes: String, hours: String): String {
    return "$hours:$minutes:$seconds"
}

fun Int.pad(): String {
    return this.toString().padStart(2, '0')
}

fun formatChargingNotificationContent(duration: Duration): String {
    duration.inWholeMilliseconds.times(0.00025F)

    return "Energy consumption : ${DecimalFormat("#.##").format(duration.inWholeMilliseconds.times(0.0000025F))}kWh\n" +
            "Current rate : RM${DecimalFormat("#.##").format(duration.inWholeMilliseconds.times(0.000035F))}"
}

fun getStateName(latitude: Double, longitude: Double, context: Context): String? {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
    return addresses?.firstOrNull()?.adminArea
}

fun setWindowBrightness(activity: ComponentActivity, brightness: Float) {
    val window = activity.window
    val layoutParams = window.attributes
    layoutParams.screenBrightness = brightness
    window.attributes = layoutParams
}

fun generateRandomDateOfMonth(year: Int, month: Int, maxDay: Int?): Date {
    val adjustedMonth = (month - 1 + 12) % 12
    val adjustedYear = year - (month - 1) / 12
    val calendar = Calendar.getInstance()
    calendar.set(adjustedYear, adjustedMonth, 1)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val maximumDay = maxDay?.let { minOf(it, daysInMonth) } ?: daysInMonth
    val randomDay = (1..maximumDay + 1).random()
    calendar.set(adjustedYear, adjustedMonth, randomDay)
    return calendar.time
}

fun dateInSpecificMonth(date: Date, specificMonth: Int): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.MONTH) + 1 == specificMonth
}