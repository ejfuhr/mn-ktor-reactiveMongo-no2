package com.example.utils

import kotlinx.html.*

fun UL.dropdown(block: LI.() -> Unit) {
    li("dropdown") {
        block()
    }
}

fun LI.dropdownToggle(block: A.() -> Unit) {
    a("#", null, "dropdown-toggle") {
        attributes["data-toggle"] = "dropdown"
        role = "button"
        attributes["aria-expanded"] = "false"

        block()

        span {
            classes = setOf("caret")
        }
    }
}

fun LI.dropdownMenu(block: UL.() -> Unit): Unit = ul("dropdown-menu") {
    role = "menu"

    block()
}

fun UL.dropdownHeader(text: String): Unit = li { classes = setOf("dropdown-header"); +text }
fun UL.divider(): Unit = li { classes = setOf("divider") }