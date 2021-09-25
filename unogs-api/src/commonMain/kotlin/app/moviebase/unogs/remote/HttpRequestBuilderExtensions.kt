package app.moviebase.unogs.remote

import app.moviebase.unogs.UnogsWebConfig
import io.ktor.client.request.*
import io.ktor.http.*

internal fun HttpRequestBuilder.endPoint(vararg paths: String) {
    url {
        takeFrom(UnogsWebConfig.BASE_URL)
        path(*paths)
    }
}
