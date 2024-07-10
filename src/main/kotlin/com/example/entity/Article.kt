package com.example.entity

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

@MappedEntity
data class Article(
    @field:Id
    @field:GeneratedValue
    val id: String? = null,
    val articleId: Long? = 101L,
    val title: String,
    val category: ArticleCategory,
    val author: Author
)

enum class ArticleCategory {
    JAVA, KOTLIN, JAVASCRIPT, BAD_FICTION
}

@Serdeable.Serializable
@Serdeable.Deserializable
data class Author(
    val firstName: String,
    val lastName: String,
    val email: String
)