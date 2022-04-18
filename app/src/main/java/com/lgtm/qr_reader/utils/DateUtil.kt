package com.lgtm.qr_reader.utils

import android.text.format.DateFormat
import java.util.*

object DateUtil {
    fun getDateTime(timestamp: Long): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = timestamp
        return DateFormat.format("yyyy-MM-dd HH:mm:ss", calendar).toString()
    }
}