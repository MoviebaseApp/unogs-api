package app.moviebase.unogs.remote

import app.moviebase.unogs.UnogsUrlParameter
import app.moviebase.unogs.UnogsWebConfig
import io.ktor.client.request.*

internal object UnogsHttpClientFactory {

    fun create(apiKey: String, logLevel: UnogsLogLevel) = buildHttpClient(logLevel) {
        it.header(UnogsUrlParameter.API_KEY, apiKey)
        it.header(UnogsUrlParameter.API_HOST, UnogsWebConfig.HOST)
        it.header(UnogsUrlParameter.USE_QUERY_STRING, true)
    }
}
