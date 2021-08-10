import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String?.formatToDate(): String? {
    return if (this != null && this != "") {
        try {
            var format = SimpleDateFormat("YYYY-MM-DD HH:mm:ss")
            val newDate: Date = format.parse(this)

            format = SimpleDateFormat("dd MMM yyyy, HH.mm")
            format.format(newDate)
        } catch (e: Exception) {
            return "-"
        }
    } else "-"
}

fun String?.formatPrice(currency: String?): String {
    return when (currency) {
        "IDR" -> "Rp ${getPrice(this?.toInt() ?: 0)}"
        else -> "-"
    }
}

private fun getPrice(price: Int): String {
    val formatter: NumberFormat = DecimalFormat("#,###")
    return formatter.format(price).toString().replace(",", ".")
}