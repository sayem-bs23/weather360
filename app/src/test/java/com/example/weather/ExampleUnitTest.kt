package com.example.weather

import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        println(current.dayOfMonth)
//        val formatted = current.format(formatter)
//        println(formatter)
//        println("Current Date is: $formatted")

        val d = Date()

        val t = d.month
        print("d.month:")
        println(t)
    }
}
