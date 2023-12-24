package xyz.andornot.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    repeat(50_000) {
        launch {
            delay(5000L)
            println(".")
        }
    }
}