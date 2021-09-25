package app.moviebase.unogs

import app.moviebase.unogs.api.UnogsApi
import app.moviebase.unogs.remote.UnogsHttpClientFactory
import app.moviebase.unogs.remote.UnogsLogLevel
import io.ktor.client.*

class Unogs(
    apiKey: String,
    logLevel: UnogsLogLevel = UnogsLogLevel.NONE
) {

    private val client: HttpClient = UnogsHttpClientFactory.create(apiKey, logLevel)

    val api = UnogsApi(client)
}

