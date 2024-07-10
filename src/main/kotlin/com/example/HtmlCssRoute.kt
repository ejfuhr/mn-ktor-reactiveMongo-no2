package com.example

import io.ktor.server.application.*
import io.ktor.server.routing.*

import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import kotlinx.css.*
import kotlinx.css.FlexBasis.Companion.auto
import kotlinx.html.*
import io.micronaut.ktor.KtorRoutingBuilder
import jakarta.inject.Singleton

@Singleton
class HtmlCssRoute : KtorRoutingBuilder({
    get("/html-dsl") {
        call.respondHtml {
            body {
                h1 { +"HTML" }
                ul {
                    for (n in 1..10) {
                        li { +"10 n here $n" }
                    }
                }
                p{
                    +"here is p should have bg color as beige"
                }
                p{
                    +"link is next to this"
                    a( href = "/styles.css"){+"should take you to styles"}
                    p{+"what is here in p"}
                    a("http://abc.com"){+"some here abc"}

                }
            }
        }
    }
    get("/styles.css") {
        call.respondCss {
            body {
                backgroundColor = Color.darkBlue
                margin(0.px)
            }

            p{
                backgroundColor = Color.beige
                width = 400.px
                //width(200.px)
                margin(20.px)
                padding(5.px)
            }

            rule("h1.page-title") {
                color = Color.yellow
            }
        }
    }
})

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}