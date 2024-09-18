package com.solodev.codingchallenge.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun formatDateTimeVersionCodeO(inputDateTime: String): String {
    val dateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ISO_DATE_TIME)
    val month = dateTime.month.toString().substring(0, 3)
    val day = dateTime.dayOfMonth.toString().padStart(2, '0')
    val year = dateTime.year

    return "$month. $day, $year"
}
