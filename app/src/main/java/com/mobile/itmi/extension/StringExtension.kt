import android.annotation.SuppressLint
import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.util.*

fun String.toHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@SuppressLint("SimpleDateFormat")
fun String?.formatToDate(): String? {
    return if (this != null && this != "") {
        try {
            var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val newDate: Date = format.parse(this)

            format = SimpleDateFormat("dd MMM yyyy, HH.mm")
            format.format(newDate)
        } catch (e: Exception) {
            return ""
        }
    } else ""
}