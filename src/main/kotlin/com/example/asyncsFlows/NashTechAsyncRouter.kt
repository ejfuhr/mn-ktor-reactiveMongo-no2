package com.example.asyncsFlows

import io.ktor.server.application.*
import io.micronaut.ktor.KtorRoutingBuilder
import jakarta.inject.Singleton
import kotlinx.coroutines.delay

import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async



@Singleton
class NashTechAsyncRouter  : KtorRoutingBuilder({
    get("/stringAsync") {
        val result1 = async { fetchData1() }
        val result2 = async { fetchData2() }

        call.respondText("Result: ${result1.await()} - ${result2.await()}")
    }

})

suspend fun fetchData1(): String {
    delay(1000)
    return "Data from source 1"
}

suspend fun fetchData2(): String {
    delay(1500)
    return "Data from source 2"
}