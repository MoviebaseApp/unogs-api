package app.moviebase.unogs.remote

import kotlinx.datetime.*

internal fun String.tryLocalDate(): LocalDate? = try {
    if (isBlank()) null else toLocalDate()
} catch (t: Throwable) {
    null
}
