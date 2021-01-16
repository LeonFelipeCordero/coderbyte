package time

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MostFreeTimeTest {

    private lateinit var mostFreeTimeTest: MostFreeTime

    @BeforeEach
    fun setUp() {
        mostFreeTimeTest = MostFreeTime()
    }

    @Test
    fun `should get most free time from list`() {
        val mostFreeTime = mostFreeTimeTest.mostFreeTime(
            listOf(
                "11:30AM-02:20PM",
                "09:50AM-10:34AM",
                "10:52AM-11:28AM",
                "09:00AM-09:20AM",
                "02:22PM-03:00PM",
                "04:22PM-06:00PM"
            )
        )
        assertThat(mostFreeTime).isEqualTo("01:22")
    }

    @Test
    fun `should return free time for two items`() {
        val mostFreeTime = mostFreeTimeTest.mostFreeTime(
            listOf(
                "02:22PM-03:00PM",
                "11:30AM-02:20PM"
            )
        )
        assertThat(mostFreeTime).isEqualTo("00:02")
    }

    @Test
    fun `should fail for one items`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                mostFreeTimeTest.mostFreeTime(listOf("11:30AM-02:20PM"))
            }
    }

    @Test
    fun `should fail if list is empty`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                mostFreeTimeTest.mostFreeTime(emptyList())
            }
    }

    @Test
    fun `should fail when time is overlapping`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                mostFreeTimeTest.mostFreeTime(
                    listOf(
                        "11:30AM-02:20PM",
                        "09:50AM-10:34AM",
                        "10:52AM-11:28AM",
                        "09:00AM-09:20AM",
                        "02:15PM-03:00PM",
                    )
                )
            }
    }
}
