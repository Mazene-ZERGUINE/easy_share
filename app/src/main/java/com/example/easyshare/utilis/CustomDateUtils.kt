package com.example.easyshare.utilis

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

object CustomDateUtils {
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
            years > 0 -> "Il y'a $years année${if (years > 1) "s" else ""}"
            weeks > 0 -> "Il y'a $weeks semaine${if (weeks > 1) "s" else ""}"
            days > 0 -> {
                val dayString = "Il y'a $days jour${if (days > 1) "s" else ""}"
                if (hours > 0) "$dayString et ${hours}h" else dayString
            }
            else -> "Il y'a ${hours}h"
        }
    }

    fun formatReadableDate(dateStr: String): String {
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH)

        val date = ZonedDateTime.parse(dateStr, inputFormatter)
        return date.format(outputFormatter)
    }
}
