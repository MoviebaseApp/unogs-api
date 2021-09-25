package app.moviebase.unogs.model

import app.moviebase.unogs.remote.LocalDateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class UnogsMediaType(val value: String) {
    @SerialName("movie")
    MOVIE("movie"),

    @SerialName("series")
    SHOW("series")
}

@Serializable
data class UnogsReleaseExpireItems(
    @SerialName("COUNT") val count: String,
    @SerialName("ITEMS") val items: List<UnogsItem>
)


@Serializable
data class UnogsDeletedItems(
    @SerialName("COUNT") val count: String,
    @SerialName("ITEMS") val items: List<UnogsItem>
)

@Serializable
data class UnogsItem(
    @SerialName("netflixid") val netflixId: String,
    @SerialName("imdbid") val imdbId: String?,
    @SerialName("title") val titleValue: String,
    val released: String,
    @SerialName("unogsdate") @Serializable(LocalDateSerializer::class) val unogsDate: LocalDate? = null,
    @SerialName("type") val type: UnogsMediaType,
    @SerialName("synopsis") val synopsis: String
) {

    val releaseYear: Int? get() = released.toIntOrNull()
    val title: String
        get() = titleValue
            .replace("&rsquo;", "'")
            .replace("&#39;", "'")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&#x27;", "'")

    val season: Int?
        get() {
            if (type != UnogsMediaType.SHOW) return null
            if (!synopsis.contains("Seasons")) return null

            val seasonText = synopsis.substringAfter("<br><b>From ")
                .substringBefore(" Seasons</b><br>")

            return seasonText.substringAfter(" to ").toIntOrNull()
        }

}

@Serializable
data class UnogsDeleted(
    @SerialName("netflixid") val netflixId: String,
    @SerialName("date") @Serializable(LocalDateSerializer::class) val date: LocalDate? = null
)

@Serializable
data class UnogsDetails(
    @SerialName("filmid") val filmId: String,
    @SerialName("imdbid") val imdbId: String,
    @SerialName("type") val type: UnogsMediaType
)
