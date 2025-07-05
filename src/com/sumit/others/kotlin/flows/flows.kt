package com.sumit.others.kotlin.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {

    val data: Flow<Int> = producer()
    data.collect {
        println("Hello $it")
    }
}

fun producer() = flow {
    val list = listOf(1, 2, 3, 45, 6, 6, 7, 7)
    for (item in list) {
        delay(1000)
        emit(item)
    }
}
