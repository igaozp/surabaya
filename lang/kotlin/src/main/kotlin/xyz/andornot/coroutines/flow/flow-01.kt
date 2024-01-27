package xyz.andornot.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private suspend fun simple(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3)
}

fun main() = runBlocking {
    simple().forEach { value -> println(value) }
}