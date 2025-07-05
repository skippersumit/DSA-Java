package com.sumit.others.kotlin.builders

import kotlinx.coroutines.*
/*
Creates a new scope and doesn't complete until all children complete.

Key differences from runBlocking:

Doesn't block the current thread while waiting for children

If any child fails, all other children are cancelled

Automatically propagates exceptions
 */

fun main() = runBlocking {
    try {
        println("Inside main")
        coroutineScope { // creates a new scope
            launch {
                delay(500L)
                println("Task 1 completed")
            }
            launch {
                delay(1000L)
                println("Task 2 completed")
            }
        }
        println("All tasks completed")
    } catch (e: Exception) {
        println("Exception caught: $e")
    }
}