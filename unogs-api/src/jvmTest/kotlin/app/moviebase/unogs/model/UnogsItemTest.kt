package app.moviebase.unogs.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class UnogsItemTest {

    @Test
    fun `it can format title with apostrophe`() {
        val classToTest = TestUnogsItem("Gabby&#39;s Dollhouse")

        assertThat(classToTest.title).isEqualTo("Gabby's Dollhouse")
    }

    @Test
    fun `it can format title with and signs`() {
        val classToTest = TestUnogsItem("Oktoberfest: Beer &amp; Blood")

        assertThat(classToTest.title).isEqualTo("Oktoberfest: Beer & Blood")
    }

    @Test
    fun `it can format title with quote`() {
        val classToTest = TestUnogsItem("It&rsquo;s a City")

        assertThat(classToTest.title).isEqualTo("It's a City")
    }

    @Test
    fun `it can extract season out of synopsis`() {
        val synopsis = """
            synopsisInvestigative journalist Paul Connolly becomes a voluntary inmate in the world&#39;s most volatile prisons, 
            where intimidation and brutality rule.<br><b>From 4 to 5 Seasons</b><br>2021-01-09 02:11:42
        """.trimIndent()

        val classToTest = TestUnogsItem(synopsis = synopsis)

        assertThat(classToTest.season).isEqualTo(5)
    }
}
