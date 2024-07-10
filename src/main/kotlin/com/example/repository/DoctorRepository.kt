package com.example.repository

import com.example.entity.Article
import com.example.entity.Doctor
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository

@MongoRepository
interface DoctorRepository: CoroutineCrudRepository<Doctor, String> {
}