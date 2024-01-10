package xyz.andornot.coroutines.context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        println("Unconfined: I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined: After delay in thread ${Thread.currentThread().name}")
    }

    launch {
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}