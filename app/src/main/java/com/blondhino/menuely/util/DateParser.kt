package com.blondhino.menuely.util

import java.text.SimpleDateFormat
import java.util.*


fun parseDate(date: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy. HH:mm")
    val formatedDate = sdf.format(Date(date * 1000))
    val dayAndMonth = formatedDate.split(" ").get(0)
    val time = formatedDate.split(" ").get(1)
    return "$dayAndMonth at $time"
}

fun generateAccountInfo(createdAt: Long?, updatedAt: Long?): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy. HH:mm")
    var formatedDate = sdf.format(createdAt?.times(1000)?.let { Date(it) })
    var dayAndMonth = formatedDate.split(" ").get(0)
    var time = formatedDate.split(" ").get(1)
    val createdAtValue = "Account created:    $dayAndMonth at $time"
    formatedDate = sdf.format(updatedAt?.times(1000)?.let { Date(it) })
    dayAndMonth = formatedDate.split(" ").get(0)
    time = formatedDate.split(" ").get(1)
    val updatedAtValue = "Account updated:  $dayAndMonth at $time"
    return "$createdAtValue\n$updatedAtValue"
}