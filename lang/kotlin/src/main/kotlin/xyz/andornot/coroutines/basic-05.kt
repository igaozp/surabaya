package xyz.andornot.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        delay(1000L)
        println("World!")
    }

    print("Hello ")
    job.join()
    println("Done")
}