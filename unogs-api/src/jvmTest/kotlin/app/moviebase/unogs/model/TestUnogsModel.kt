package app.moviebase.unogs.model

fun TestUnogsItem(title: String = "Gabby&#39;s Dollhouse", synopsis: String = "") = UnogsItem(
    netflixId = "81009946",
    imdbId = "tt5209032",
    titleValue = title,
    released = "2021",
    unogsDate = kotlinx.datetime.LocalDate.parse("2021-01-06"),
    type = UnogsMediaType.SHOW,
    synopsis = synopsis
)
