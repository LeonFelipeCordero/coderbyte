package second

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SecondGreatLowTest {
    private lateinit var secondGreatLow: SecondGreatLow

    @BeforeEach
    fun setUp() {
        secondGreatLow = SecondGreatLow()
    }

    @Test
    fun `should get second great and low`() {
        val greatLow = secondGreatLow.calculate(listOf(7, 199, 31, 7, 233))
        Assertions.assertThat(greatLow.lower).isEqualTo(31)
        Assertions.assertThat(greatLow.greater).isEqualTo(199)
    }

    @Test
    fun `should work well with two items list`() {
        val greatLow = secondGreatLow.calculate(listOf(7, 31))
        Assertions.assertThat(greatLow.lower).isEqualTo(31)
        Assertions.assertThat(greatLow.greater).isEqualTo(7)
    }

    @Test
    fun `should work well with single item list`() {
        val greatLow = secondGreatLow.calculate(listOf(7))
        Assertions.assertThat(greatLow.lower).isEqualTo(7)
        Assertions.assertThat(greatLow.greater).isEqualTo(7)
    }

    @Test
    fun `should fail if list is empty`() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { secondGreatLow.calculate(emptyList()) }
    }
}
