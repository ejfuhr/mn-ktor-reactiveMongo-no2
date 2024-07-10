package com.example
import io.ktor.server.netty.NettyApplicationEngine
import io.micronaut.ktor.KtorApplication
import io.micronaut.ktor.runApplication
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class KtorApp : KtorApplication<NettyApplicationEngine.Configuration>({
    applicationEngineEnvironment { //
        log = LoggerFactory.getLogger(Application::class.java)
        log.info("in JtorApp...")

    }

    applicationEngine {
        workerGroupSize = 10
    }
})
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        runApplication(args)
    }
}

