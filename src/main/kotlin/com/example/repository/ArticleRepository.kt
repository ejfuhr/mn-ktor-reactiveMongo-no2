package com.example.repository

import com.example.entity.Article
import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import kotlinx.coroutines.flow.Flow

import reactor.core.publisher.Flux

@MongoRepository
interface ArticleRepository : CoroutineCrudRepository<Article, String> {
    @MongoFindQuery("{ title: { \$regex: :title, '\$options' : 'i'}}")
    fun findByTitleLikeCaseInsensitive(title: String): Flow<Article>

    @MongoFindQuery("{ articleId: { \$regex: :articleId, '\$options' : 'i'}}")
    fun findByArticleId(articleId:Long):Flow<Article>
}