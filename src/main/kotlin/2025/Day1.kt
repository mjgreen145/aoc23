package `2025`

import readLines
import println
import kotlin.math.absoluteValue
import kotlin.time.measureTime

fun main() {
    val exampleInput = readLines("2025", "day1-example")
    val input = readLines("2025", "day1")

    fun toNumber(rotation: String): Int {
        val char = rotation.first()
        val number = rotation.substring(1).toInt()
        return number.times(if (char == 'R') 1 else -1)
    }

    fun part1(lines: List<String>): Int {
        var zeros = 0;
        lines.map(::toNumber).fold(50) { acc, next ->
            val new = (acc + next).mod(100)
            if (new == 0) zeros++
            new
        }
        return zeros
    }

    fun part2(lines: List<String>): Int {
        var zeros = 0;
        lines.map(::toNumber).fold(50) { acc, next ->
            val new = (acc + next)
            if (new >= 100) zeros += new.div(100)
            if (new <= 0) zeros += new.div(100).absoluteValue + if (acc == 0) 0 else 1
            new.mod(100)
        }
        return zeros
    }

    val part1Example = part1(exampleInput)
    val part2Example = part2(exampleInput)

    check(part1Example == 3) { -> "Part 1 example failed: Expected 3, received $part1Example" };
    check(part2Example == 6) { -> "Part 2 example failed: Expected 6, received $part2Example" };

    val timePart1 = measureTime { part1(input).println() }
    val timePart2 = measureTime { part2(input).println() }

    println("Part 1 took $timePart1")
    println("Part 2 took $timePart2")
}