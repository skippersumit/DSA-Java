package com.sumit.others.kotlin.exceptionHandling

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            throw RuntimeException("Oops!")
        } catch (e: Exception) {
            println("Caught inside coroutine: $e")
        }
    }
    job.join()
}