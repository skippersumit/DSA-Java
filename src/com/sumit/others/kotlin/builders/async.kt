package com.sumit.others.kotlin.builders

import kotlinx.coroutines.*

/*
Key points:
Used when you need a result from the coroutine.

Returns a Deferred<T> (a light-weight non-blocking future)

Use await() to get the result

If an exception occurs, it's thrown when you call await()*/

fun main() = runBlocking {
    println("Main starts")

    val deferredResult: Deferred<Int> = async {
        println("Calculation starts")
        delay(1000L) // simulate long computation
        42 // this value will be returned
    }

    println("Main continues while calculation is running")
    val result = deferredResult.await() // wait and get the result
    println("Result is $result")
}