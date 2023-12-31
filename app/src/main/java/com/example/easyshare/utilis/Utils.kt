import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.easyshare.R
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object Utils {
    fun displayToast(
        context: Context,
        layoutResId: Int,
        message: String,
        duration: Int
    ) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(layoutResId, null)
        val messageTextView: TextView = layout.findViewById(R.id.messageTextView)

        messageTextView.text = message
        val toast = Toast(context)

        toast.duration = duration
        toast.view = layout
        toast.show()
    }


    fun calculateTimeDifference(pastDateStr: String): String {
        val pastDate = ZonedDateTime.parse(pastDateStr, DateTimeFormatter.ISO_DATE_TIME)
        val currentTime = ZonedDateTime.now(pastDate.zone)

        val totalHours = ChronoUnit.HOURS.between(pastDate, currentTime)
        val totalDays = totalHours / 24
        val years = totalDays / 365
        val daysInCurrentYear = totalDays % 365
        val weeks = daysInCurrentYear / 7
        val days = daysInCurrentYear % 7
        val hours = totalHours % 24

        return when {
            years > 0 -> "Il y'a ${years} année${if (years > 1) "s" else ""}"
            weeks > 0 -> "Il y'a ${weeks} semaine${if (weeks > 1) "s" else ""}"
            days > 0 -> {
                val dayString = "Il y'a ${days} jour${if (days > 1) "s" else ""}"
                if (hours > 0) "$dayString et ${hours}h" else dayString
            }
            else -> "Il y'a ${hours}h"
        }
    }

}
