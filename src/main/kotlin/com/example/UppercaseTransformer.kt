    package com.example
import jakarta.validation.constraints.NotBlank
import jakarta.inject.Singleton

@Singleton
open class UppercaseTransformer : NameTransformer {
    override fun transform(@NotBlank name: String): String {
        return name.uppercase()
    }
}
