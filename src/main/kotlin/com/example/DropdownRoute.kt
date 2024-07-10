package com.example

import com.example.utils.divider
import com.example.utils.dropdown
import com.example.utils.dropdownHeader
import com.example.utils.dropdownMenu
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.micronaut.ktor.KtorRoutingBuilder
import jakarta.inject.Singleton
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument

@Singleton
class DropdownRoute : KtorRoutingBuilder({
    get("/dropdown1") {
        call.respondHtml {
            //createHTMLDocument().html {

                body {
                    h1 { +"HTML" }

                    ul {
                        dropdown {
                            dropdownMenu {
                                li { a("#") { +"Action" } }
                                li { a("#") { +"Another action" } }
                                li { a("#") { +"Something else here" } }
                                divider()
                                dropdownHeader("Nav header")
                                li { a("#") { +"Separated link" } }
                                li { a("#") { +"One more separated link" } }
                            }
                        }
                    }
                }
            }
        }
    //}
})