    package com.example

import jakarta.validation.constraints.NotBlank

interface NameTransformer {
    fun transform(@NotBlank name: String) : String
}