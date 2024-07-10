plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.23"
    id("com.google.devtools.ksp") version "1.9.23-1.0.19"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.0"
    //id("io.micronaut.test-resources") version "4.4.0"
    id("io.micronaut.aot") version "4.4.0"
}

version = "0.1"
group = "com.example"

val kotlinVersion=project.properties.get("kotlinVersion")

val kotlinx_html_version: String by project
val kotlin_css_version: String by project

var mockkVersion = "1.13.7"
var coroutines_version = "1.9.0-RC"


repositories {
    mavenCentral()
	maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
    ksp("io.micronaut.data:micronaut-data-document-processor")
    ksp("io.micronaut:micronaut-http-validation")
    ksp("io.micronaut.serde:micronaut-serde-processor")
	
		    implementation("io.ktor:ktor-server-html-builder-jvm")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinx_html_version")
    //implementation("io.ktor:ktor-server-core-jvm")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.148-kotlin-1.4.30")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:$kotlin_css_version")

    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
	
    implementation("io.ktor:ktor-serialization-jackson-jvm:2.3.11")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.11")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.11")
    implementation("io.micrometer:context-propagation")
    implementation("io.micronaut.data:micronaut-data-mongodb")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.kotlin:micronaut-ktor")
    implementation("io.micronaut.mongodb:micronaut-mongo-reactive")
    implementation("io.micronaut.reactor:micronaut-reactor")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
	
    compileOnly("io.micronaut:micronaut-http-client")
	
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.mongodb:mongodb-driver-reactivestreams")
    runtimeOnly("org.yaml:snakeyaml")
	
    testImplementation("io.micronaut:micronaut-http-client")
	
	testImplementation("org.junit.jupiter:junit-jupiter-params")
	 testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation ("io.rest-assured:kotlin-extensions:5.3.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
	
}


application {
    mainClass = "com.example.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false
micronaut {
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}


