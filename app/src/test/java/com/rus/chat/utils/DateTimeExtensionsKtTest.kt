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
        assertEquals(false, dateTime.isToday())
    }

    @Test
    fun testIsYesterday() {
        var dateTime = DateTime.parse("2016-07-31T14:52:49.943Z")
        assertEquals(false, dateTime.isYesterday())
        dateTime = DateTime.parse("2016-08-01T14:52:49.943Z")
        assertEquals(true, dateTime.isYesterday())
    }

    @Test
    fun testToChatTime() {
        var dateTime = DateTime.parse("2016-07-31T14:52:49.943Z")
        assertEquals("31 Jul", dateTime.toChatTime())
    }

    companion object {
        val PATTERN: String = "dd:mm:yyyy"
    }
}