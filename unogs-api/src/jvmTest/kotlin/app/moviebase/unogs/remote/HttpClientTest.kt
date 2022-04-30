package app.moviebase.unogs.remote

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import java.io.File

fun mockHttpClient(
    responses: Map<String, String>
) = HttpClient(MockEngine) {
    val jsonFiles = mutableMapOf<String, String>()
    responses.entries.forEach {
        jsonFiles["https://unogs-unogs-v1.p.rapidapi.com/${it.key}"] = it.value
    }
    val headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

    install(JsonFeature) {
        serializer = KotlinxSerializer(buildJson())
    }

    engine {
        addHandler { request ->
            val url = request.url.toString().decodeURLPart()

            val fileName = jsonFiles[url] ?: error("Unhandled url $url")
            val file = File("./src/jvmTest/resources/unogs/$fileName")
            val content = file.readText()
            respond(content = content, headers = headers)
        }
    }
}
