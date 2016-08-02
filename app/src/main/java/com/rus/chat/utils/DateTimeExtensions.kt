package com.rus.chat.utils

import org.joda.time.DateTime
import org.joda.time.LocalDate
import java.util.*

/**
 * Created by RUS on 30.07.2016.
 */
fun DateTime.isToday(): Boolean {
    val today = LocalDate.now()
    return this.toLocalDate().equals(today)
}

fun DateTime.isYesterday(): Boolean = this.plusDays(1).isToday()

fun DateTime.toChatTime(): String {
    if(this.isToday())
        return "Today"
    else if(this.isYesterday())
        return "Yesterday"
    else return this.toString("dd MMM", Locale.ENGLISH)
}