package com.example

import com.example.entity.Doctor
import com.example.repository.DoctorRepository
import com.example.service.DoctorService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.micronaut.ktor.KtorRoutingBuilder
import io.ktor.server.routing.*
import jakarta.inject.Singleton


//val log = LoggerFactory.getLogger(DoctorServiceRoute::class.java)

@Singleton
open class DoctorServiceRoute(private val docService:DoctorService,
    private val docRepo:DoctorRepository) : KtorRoutingBuilder({

        post("/doctors/{name}"){
            val name = call.parameters["name"]
            val doc = name?.let { it1 ->
                val d  = Doctor(null, it1)
                docService.create(d)
            }
            doc?.let { it1 -> docService.create(it1) }
            call.respondText(doc.toString(), contentType = ContentType.Text.Plain)
        }

    get("/doctors"){
        val docs = docService.findAll()
        val allDocs = mutableListOf<Doctor>()
        docs
            .collect{
                allDocs.add(it)
            }

        call.respondText(allDocs.toString(), ContentType.Text.Plain)
    }
})