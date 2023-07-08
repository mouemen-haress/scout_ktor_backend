package com.acout.utils

import kotlin.random.Random

object PasswordHelper {

    fun getRandomPassWord(length: Int): String {
        val charPool = ('A'..'Z') + ('a'..'z') + ('0'..'9') + listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')')

        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}