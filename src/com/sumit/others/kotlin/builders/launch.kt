package com.sumit.others.kotlin.builders

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Key points:
//
//Returns a Job object that can be used to cancel or wait for completion
//
//Doesn't return any result (use async if you need a result)
//
//Use join() to wait for completion
// launch -> fire and forget

fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")

    val job = launch { // launch returns a Job object
        println("Coroutine starts: ${Thread.currentThread().name}")
        delay(1000L) // simulate work
        println("Coroutine ends: ${Thread.currentThread().name}")
    }

    println("Main continues: ${Thread.currentThread().name}")
    job.join() // wait for the coroutine to complete
    println("Main ends")
    /*Main starts: main
Main continues: main
Coroutine starts: main
Coroutine ends: main
Main ends

*/
}