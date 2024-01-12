package xyz.andornot.coroutines.context

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

// run with -Dkotlinx.coroutines.debug
fun main() = runBlocking {
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }

    val b = async {
        log("I'm computing another piece of the answer")
        7
    }

    log("The answer is ${a.await() * b.await()}")
}