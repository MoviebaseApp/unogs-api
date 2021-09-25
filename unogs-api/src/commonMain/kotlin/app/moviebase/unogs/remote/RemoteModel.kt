package app.moviebase.unogs.remote

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

enum class UnogsLogLevel {
    ALL,
    HEADERS,
    BODY,
    INFO,
    NONE,
}

internal fun buildHttpClient(
    logLevel: UnogsLogLevel = UnogsLogLevel.NONE,
    interceptor: suspend (HttpRequestBuilder) -> Unit
): HttpClient {

    val json = buildJson()

    val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = logLevel.ktorLogLevel
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
            connectTimeoutMillis = 60_000
            socketTimeoutMillis = 60_000
        }

    }
    httpClient.requestPipeline.intercept(HttpRequestPipeline.Render) {
        interceptor(context)
    }
    return httpClient
}

private val UnogsLogLevel.ktorLogLevel
    get() = when (this) {
        UnogsLogLevel.ALL -> LogLevel.ALL
        UnogsLogLevel.HEADERS -> LogLevel.HEADERS
        UnogsLogLevel.BODY -> LogLevel.BODY
        UnogsLogLevel.INFO -> LogLevel.INFO
        UnogsLogLevel.NONE -> LogLevel.NONE
    }

internal fun buildJson(): Json = Json {
    encodeDefaults = false
    ignoreUnknownKeys = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    prettyPrint = false
}
