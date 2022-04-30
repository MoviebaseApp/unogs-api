package app.moviebase.unogs.api

import app.moviebase.unogs.model.UnogsDeletedItems
import app.moviebase.unogs.model.UnogsDetails
import app.moviebase.unogs.model.UnogsReleaseExpireItems
import app.moviebase.unogs.remote.endPoint
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class UnogsApi(private val client: HttpClient) {

    suspend fun getNewReleases(countryCode: String, days: Int, page: Int): UnogsReleaseExpireItems = client.get {
        val unogsCountry = countryCode.toUnogsCountry()

        endPoint("aaapi.cgi")
        parameter("q", "get:new${days}:${unogsCountry}")
        parameter("p", page)
        parameter("t", "ns")
        parameter("st", "adv")
    }.body()

    suspend fun getSeasonReleaseDates(countryCode: String, days: Int, page: Int): UnogsReleaseExpireItems = client.get {
        val unogsCountry = countryCode.toUnogsCountry()

        endPoint("aaapi.cgi")
        parameter("q", "get:seasons${days}:$unogsCountry")
        parameter("p", page)
        parameter("t", "ns")
        parameter("st", "adv")
    }.body()

    suspend fun getExpiring(countryCode: String, page: Int): UnogsReleaseExpireItems = client.get {
        val unogsCountry = countryCode.toUnogsCountry()

        endPoint("aaapi.cgi")
        parameter("q", "get:exp:$unogsCountry")
        parameter("p", page)
        parameter("t", "ns")
        parameter("st", "adv")
    }.body()

    /**
     * Deleted items on Netflix.
     * Doesn't have pages here.
     */
    suspend fun getDeleted(days: Int, countryCode: String): UnogsDeletedItems = client.get {
        val unogsCountry = countryCode.toUnogsCountry()

        endPoint("aaapi.cgi")
        parameter("t", "deleted")
        parameter("cl", unogsCountry)
        parameter("st", days.toString())
    }.body()

    suspend fun getDetails(imdbOrNetflixId: String): UnogsDetails = client.get {
        endPoint("aaapi.cgi")
        parameter("q", imdbOrNetflixId)
        parameter("t", "getimdb")
    }.body()

    private fun String.toUnogsCountry(): String = if (this == "GB") "UK" else this
}
