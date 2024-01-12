package xyz.andornot.coroutines.context

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

// run with -Dkotlinx.coroutines.debug
fun main() = runBlocking {
    println("My job is ${coroutineContext[Job]}")
}