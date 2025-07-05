package com.sumit.others.kotlin.channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

val channel = Channel<Int>()

fun main(): kotlin.Unit = runBlocking {
    launch {
        producer()
    }
    launch {
        consumer()
    }
}

suspend fun producer() {
    channel.send(1)
    channel.send(2)
}

suspend fun consumer() {
    println("${channel.receive()}")
    println("${channel.receive()}")
}
