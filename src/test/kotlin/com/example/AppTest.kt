package com.example

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppTest {

    private val log = LoggerFactory.getLogger(AppTest::class.java)

    @Inject
    lateinit var application: EmbeddedApplication<*>


    @Inject
    @Client("/")
    lateinit var client: HttpClient  // regular client

    @Test
    fun testItWorks() {
        assertTrue(application.isRunning)

    }

    @Test
    fun testApp() {
        val nameR = NameRequest("chuckie")

        log.info("log work??")
        val str: String = client.toBlocking().retrieve("/")
        println("test $str")
        val expect =  """
                {
                  "name" : "chuckie"
                }
            """

        assertEquals(
            expect.trimIndent(),
            //nameR,
            client.toBlocking().retrieve("/")
        )
    }
}