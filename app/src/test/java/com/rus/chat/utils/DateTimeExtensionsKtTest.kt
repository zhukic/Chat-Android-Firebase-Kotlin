package com.rus.chat.utils

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormatter
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by RUS on 30.07.2016.
 */
class DateTimeExtensionsKtTest {

    @Test
    fun testIsToday() {
        val dateTime = DateTime.parse("2016-07-31")
        assertEquals(true, dateTime.isToday())
    }

    companion object {
        val PATTERN: String = "dd:mm:yyyy"
    }
}