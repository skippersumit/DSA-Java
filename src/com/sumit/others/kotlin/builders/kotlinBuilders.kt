package com.sumit.others.kotlin.builders

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun fetchUser(): String {
    delay(1000L)
    return "User123"
}

suspend fun fetchPosts(): List<String> {
    delay(1500L)
    return listOf("Post1", "Post2")
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        coroutineScope {
            // Launch a fire-and-forget logging task
            launch {
                println("Fetching data...")
            }

            // Fetch data concurrently
            val userDeferred = async { fetchUser() }
            val postsDeferred = async { fetchPosts() }

            // Wait for both results
            val user = userDeferred.await()
            val posts = postsDeferred.await()

            println("User: $user, Posts: $posts")
        }
    }
    println("Completed in $time ms") // ~1500ms instead of 2500ms
}