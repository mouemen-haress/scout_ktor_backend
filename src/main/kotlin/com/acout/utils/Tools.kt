package com.acout.utils

object Tools {
    fun parseToInt(value: Any): Int? {
        val stringValue = value.toString()
        return stringValue.toIntOrNull()
    }

    fun convertToInt(value: Any): Int? {
        return value.toString().toIntOrNull()
    }
}