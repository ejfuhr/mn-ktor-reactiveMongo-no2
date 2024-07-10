package com.example

import com.example.utils.*
import com.example.utils.divider
import com.example.utils.dropdown
import com.example.utils.dropdownHeader
import com.example.utils.dropdownMenu
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
class HtmlKotxTest {

    @Test
    fun createHtmlTest() {
        val output = createHTML()
            .ul {
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

        assertNotNull(output)
        println("$output")
    }

}


