package com.rus.chat.utils

import org.joda.time.DateTime
import java.util.*

/**
 * Created by RUS on 30.07.2016.
 */
fun DateTime.isToday(): Boolean {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return this.year.equals(year) && this.monthOfYear.equals(month) && this.dayOfMonth.equals(day)
}

fun DateTime.isYesterday(): Boolean = this.plusDays(1).isToday()
