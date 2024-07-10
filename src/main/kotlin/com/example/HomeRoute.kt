package com.example
import io.ktor.server.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jakarta.inject.Singleton
import io.micronaut.ktor.KtorRoutingBuilder
import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger(HomeRoute::class.java)

@Singleton
class HomeRoute(private val nameTransformer: NameTransformer) : KtorRoutingBuilder({
    get("/") {
        //call.respond("on get")
        val nameR = NameRequest("chuckie")
        call.respond(nameR)
    }
    post("/params") {
        val params = call.parameters["name"]
        val nameP = params //params["name"]
        println("here is nameP $nameP")
        call.respondText("The '$nameP' account is created", contentType = ContentType.Text.Plain)

    }
    post("/body"){
        val name = call.receive<NameRequest>().name
        println("name $name")
        try {
            call.respondText(nameTransformer.transform(name), contentType = ContentType.Text.Plain)
        } catch (e: ConstraintViolationException) {
            call.respondText(
                e.constraintViolations
                    .joinToString(",")
                    { c -> "${c.propertyPath.last().name} ${c.message}" },
                contentType = ContentType.Text.Plain, status = HttpStatusCode.UnprocessableEntity
            )
        }
    }
    post("/") {
        val name = call.receive<NameRequest>().name
        val nm2 = call.receiveParameters().toString()
        println("name $name")
        println("params $nm2")
        log.info("name $name")
        log.info("params $nm2")
        try {
            call.respondText(nameTransformer.transform(name), contentType = ContentType.Text.Plain)
        } catch (e: ConstraintViolationException) {
            call.respondText(
                e.constraintViolations
                    .joinToString(",")
                    { c -> "${c.propertyPath.last().name} ${c.message}" },
                contentType = ContentType.Text.Plain, status = HttpStatusCode.UnprocessableEntity
            )
        }
    }
})
data class NameRequest(val name: String)
