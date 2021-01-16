package time

import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MostFreeTime {

    companion object {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mma")
        const val timeDelimiter: String = "-"
    }

    fun mostFreeTime(times: List<String>): String {
        if (times.isEmpty() || times.size == 1) throw IllegalArgumentException("List can not be emtpy or have just one item")

        val sortedTimes = sortTimes(times)
        isTimeOverlapping(sortedTimes)
        val maxDuration = getMaxDuration(sortedTimes)

        return LocalTime.of(maxDuration!!.toHoursPart(), maxDuration.toMinutesPart()).toString()
    }

    private fun sortTimes(times: List<String>) =
        times.map {
            it.split(timeDelimiter)
        }.map {
            it.map { timeString -> LocalTime.parse(timeString.trim(), formatter) }
        }.sortedBy { it[0] }

    private fun isTimeOverlapping(sortedTimes: List<List<LocalTime>>) {
        sortedTimes.forEachIndexed { index, element ->
            if (index == sortedTimes.size - 1) return
            if (element[1].isAfter(sortedTimes[index + 1][0])) throw IllegalArgumentException("List can not have overlapping times")
        }
    }

    private fun getMaxDuration(sortedTimes: List<List<LocalTime>>) =
        sortedTimes.mapIndexed { index, element ->
            if (index == sortedTimes.size - 1) {
                Duration.ZERO
            } else {
                Duration.between(element[1], sortedTimes[index + 1][0])
            }
        }.maxByOrNull { it }
}
