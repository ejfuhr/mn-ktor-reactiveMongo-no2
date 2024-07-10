package com.example

import com.example.entity.Doctor
import com.example.repository.DoctorRepository
import com.example.service.DoctorService
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.micronaut.ktor.KtorRoutingBuilder
import jakarta.inject.Singleton

import kotlinx.html.*

//(private val doctorService: DoctorService)
@Singleton
class DoctorCssRoute : KtorRoutingBuilder({

        get("/html-css-dsl") {

            val doc = Doctor("10101", "John")
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                }
                body {
                    p {
                        +"here is para that should use styles.css"
                    }
                    h1(classes = "page-title") {
                        +"Hello from Ktor!"
                    }
                    h1(classes = "page-title") {
                        +"Hello from Ktor2 !".plus(doc.name)
                    }
                }
            }
        }
    }
)