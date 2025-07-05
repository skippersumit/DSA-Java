package com.sumit.others

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        launch {
            delay(100)
            println("World")
        }
        println("Hello")
    }
    println("Main function ends here")
}