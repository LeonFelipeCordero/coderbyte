package second

class SecondGreatLow {

    fun calculate(values: List<Int>): GreatLow =
        when (values.size) {
            0 -> throw IllegalArgumentException("List can not be emtpy")
            1 -> GreatLow(values[0], values[0])
            2 -> GreatLow(values[1], values[0])
            else -> {
                val sorted = values.toMutableSet().sorted()
                GreatLow(sorted[1], sorted[sorted.size - 2])
            }
        }
}
