package com.example

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.micronaut.ktor.KtorRoutingBuilder
import jakarta.inject.Singleton
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.margin
import kotlinx.css.*
import kotlinx.css.properties.Timing.Companion.ease
import kotlinx.css.properties.animation
import kotlinx.css.properties.s
import kotlinx.html.*

@Singleton
class SlideCssRoute : KtorRoutingBuilder({
    get("/slide-test") {
        call.respondHtml {
            head {
                link(rel = "stylesheet", href = "/sliders.css", type = "text/css")
            }
            body {
                p {
                    +"this should be sliding from right to left"
                }
            }
        }

    }
    get("/sliders.css") {
        call.respondCss {
            body {
                backgroundColor = Color.beige
                margin(10.px)
            }
            p {
                animation(
                    name = "slidein",
                    duration = 3.s
                )

            }
            /*            rule("x",true, RuleSet{
                            from {
                                margin-left: 100%;
                                width: 300%;
                            }
                        })
            rule("@keyframes slidein") {

            }*/
        }
    }
})

/*
<html>
<head>
<style>
   p {
      animation-duration: 3s;
      animation-name: slidein;
   }

   @keyframes slidein {
      from {
         margin-left: 100%;
         width: 300%;
      }

      to {
         margin-left: 0%;
         width: 100%;
      }
   }
</style>
</head>
<body>
   <p>
      The text appears from right to left
      </p>
</body>
</html>
 */