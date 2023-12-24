package xyz.andornot.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        doWorld()
    }
    print("Hello ")
}

private suspend fun doWorld() {
    delay(1000L)
    println("World!")
}