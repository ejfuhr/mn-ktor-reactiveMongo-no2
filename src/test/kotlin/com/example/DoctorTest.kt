package com.example

import com.example.entity.Doctor
import com.example.repository.DoctorRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory

@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoctorTest {

    private val log = LoggerFactory.getLogger(DoctorTest::class.java)

    @Inject
    lateinit var docRep:DoctorRepository

    @BeforeAll
    fun setUp(){
        var number = 0
        runBlocking {
            number = docRep.deleteAll()
        }
        log.info("no of deleted doctors $number")
    }

    @Test
    fun `simple repo add Doctor`() {
        var doc1 = Doctor(null, "dork")
        var doc2 = Doctor(null, "dorque")

        runBlocking {
            doc1 = docRep.save(doc1)
            doc2 = docRep.save(doc2)
        }
        assertNotNull(doc1.id)
        log.info("gotta doc ${doc1.id}")
    }
}