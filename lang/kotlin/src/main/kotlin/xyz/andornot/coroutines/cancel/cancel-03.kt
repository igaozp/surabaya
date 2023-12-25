package xyz.andornot.coroutines.cancel

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        repeat(5) { i ->
            try {
                println("job: I'm sleeping $i ...")
                delay(500)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}