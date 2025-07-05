package com.sumit.others.kotlin.builders

import kotlinx.coroutines.*
/*
Creates a coroutine and blocks the current thread until completion.
Key points:

Used mainly in main() functions and tests

Bridges regular blocking code to suspending functions

Avoid using in production coroutine code (use only at the top level)
Before runBlocking: main
Inside runBlocking: main
After launch
Inside launch
After runBlocking
 */

fun main() {
    println("Before runBlocking: ${Thread.currentThread().name}")

    runBlocking { // blocks the main thread
        println("Inside runBlocking: ${Thread.currentThread().name}")
        launch {
            delay(1000L)
            println("Inside launch")
        }
        println("After launch")
    }

    println("After runBlocking")
}