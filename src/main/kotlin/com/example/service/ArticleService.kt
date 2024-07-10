package com.example.service

import com.example.entity.Article
import com.example.repository.ArticleRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
open class ArticleService(
    private val articleRepository: ArticleRepository
) {
    open suspend fun create(article: Article): Article =
        articleRepository.save(article)

    open fun findAll(): Flow<Article> =
        articleRepository.findAll()

    open suspend fun findById(id: String): Article? =
        articleRepository.findById(id)

    open suspend fun update(id: String, updated: Article): Article =
        findById(id)
            .let { foundArticle ->
                if (foundArticle != null) {
                    updated.copy(id = foundArticle.id)
                }
                articleRepository.update(
                    foundArticle?.copy(id = foundArticle?.id) ?: throw Exception("not found")
                )
            }

    open suspend fun deleteById(id: String) {
        val articleD = findById(id)
        articleD
            .let { it->
                if (it != null)
                    articleD?.let { articleRepository.delete(it) }
                else
                    throw
                        HttpStatusException(HttpStatus.NOT_FOUND, "Article with id: $id was not deleted.")

            }
    }

    open fun findByTitleLike(name: String): Flow<Article> =
        articleRepository.findByTitleLikeCaseInsensitive(name)

    open suspend fun trialUpdate(id: String): Article {
        val article = articleRepository.findById(id)
        return if (article == null) {
            throw Exception("x")
        } else {
            articleRepository.update(
                article.copy(id = article.id)
            )
        }

    }
}


