package com.example

import com.example.entity.Article
import com.example.entity.ArticleCategory
import com.example.entity.Author
import com.example.repository.ArticleRepository
import com.example.repository.DoctorRepository
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.css.varTag
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.slf4j.LoggerFactory

@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ArticleTest {
    private val log = LoggerFactory.getLogger(ArticleTest::class.java)

/*    @Inject
    lateinit var application: EmbeddedApplication<*>*/

    @Inject
    lateinit var articleRepo: ArticleRepository

    @BeforeAll
    fun setUp() {
        var number = 0
        runBlocking {

            number = articleRepo.deleteAll()
        }
        log.info("no of deleted doctors $number")

    }

    @Test
    @Order(1)
    fun `save articles`() {
        var auth1 = Author("ch", "chan", "xyz@xyz.com")
        var auth2 = Author("ch2", "chan2", "xyz@xyz.com")
        var article1 = Article(null, 101L, "wild times", ArticleCategory.JAVA, auth1)
        var article2 = Article(null, 102L, "wild times 2", ArticleCategory.BAD_FICTION, auth2)
        var artList = listOf(article1, article2)
        assertNotNull(artList)

       runBlocking {

            articleRepo.save(article1)
            articleRepo.save(article2)

/*            var articleFlow = articleRepo.findAll()
                .map { it ->
                    log.info("here is article ${it.title}")

                }*/
        }

    }


}