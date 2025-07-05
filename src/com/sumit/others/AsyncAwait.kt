package com.sumit.others

import kotlinx.coroutines.*

suspend fun fetchData(): String {
    delay(1000L)
    return "Data loaded"
}

fun main() = runBlocking {
    val result = async { fetchData() }
    println("Waiting for data...")
    println(result.await())
}