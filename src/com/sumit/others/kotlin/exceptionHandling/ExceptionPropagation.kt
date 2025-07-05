package com.sumit.others.kotlin.exceptionHandling

import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, e ->
        println("Caught $e")
    }

    val job = GlobalScope.launch(handler) {
        throw RuntimeException("Failed")
    }
    job.join()
}