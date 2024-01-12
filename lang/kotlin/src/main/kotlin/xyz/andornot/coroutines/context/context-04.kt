package xyz.andornot.coroutines.context

import kotlinx.coroutines.*

private fun log(msg: String) = println("[${Thread.currentThread().name} $msg]")

// run with -Dkotlinx.coroutines.debug
@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun main() {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in ctx1")
                withContext(ctx2) {
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }
}