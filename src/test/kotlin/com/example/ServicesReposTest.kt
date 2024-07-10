package com.example

import com.example.entity.Doctor
import com.example.repository.DoctorRepository
import com.example.service.DoctorService
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory

import io.ktor.server.testing.*
import kotlin.test.assertContains

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServicesReposTest {

    private val log = LoggerFactory.getLogger(ServicesReposTest::class.java)

    @Inject
    private lateinit var docService:DoctorService
    @Inject
    private lateinit var docRepo:DoctorRepository

    @Test
    fun testRoot() = testApplication{
        application {
            //module()
        }
    }

    @Test
    fun `check doc service`() {
        val doc = Doctor(null, "wild1")

        assertNotNull(docService)
        assertNotNull(docRepo)
        runBlocking {
            docService.create(doc)
        }
    }
}