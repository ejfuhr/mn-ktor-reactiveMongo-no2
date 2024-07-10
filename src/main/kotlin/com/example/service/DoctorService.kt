package com.example.service

import com.example.entity.Article
import com.example.entity.Doctor
import com.example.repository.DoctorRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.css.article

@Singleton
open class DoctorService(private val doctorRepo: DoctorRepository) {

    open suspend fun create(doctor: Doctor): Doctor =
        doctorRepo.save(doctor)

    open fun findAll(): Flow<Doctor> =
        doctorRepo.findAll()

    open suspend fun findById(id: String): Doctor? =
        doctorRepo.findById(id)

    open suspend fun update(id: String, updated: Doctor): Doctor =
        findById(id)
            .let { foundDoc ->
                if (foundDoc != null) {
                    updated.copy(id = foundDoc.id)
                }
                doctorRepo.update(
                    foundDoc?.id?.let { foundDoc?.copy(id = it) } ?: throw Exception("not found")
                )
            }

    open suspend fun deleteById(id: String) {
        val articleD = findById(id)
        articleD
            .let { it ->
                if (it != null)
                    articleD?.let { doctorRepo.delete(it) }
                else
                    throw HttpStatusException(HttpStatus.NOT_FOUND, "Article with id: $id was not deleted.")

            }
    }

    /*    fun findByTitleLike(name: String): Flow<Article> =
            articleRepository.findByTitleLikeCaseInsensitive(name)*/

    open suspend fun trialUpdate(id: String): Doctor {
        val doc = doctorRepo.findById(id)
        return if (doc == null) {
            throw Exception("x")
        } else {
            doctorRepo.update(
                doc.copy(id = doc.id)
            )
        }

    }
}



