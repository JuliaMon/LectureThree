package ru.netology

fun main() {
    var seconds = 2592000L;

    println(agoToText(seconds))
}

fun agoToText(seconds:Long):String {
    return when (seconds){
        in 0..60 -> "был(а) только что"
        in 61 until 60 * 60 -> compileMinutesText(seconds)
        in 60 * 60  until  24 * 60 * 60 -> compileHoursText(seconds)
        in 24 * 60 * 60  until 48 * 60 * 60 -> "был(а) в сети сутки назад"
        in 48 * 60 * 60  until 72 * 60 * 60 -> "был(а) в сети вчера"
        else -> "был(а) в сети давно"
    }
}

fun compileMinutesText(seconds: Long): String {
    val minutes = seconds / 60
    val lastDigit = minutes % 10

    var textMinutes = when {
        (lastDigit == 1L) -> "минуту"
        (lastDigit in 2L..4L) -> "минуты"
        else -> "минут"
    }

    return "был(а) в сети $minutes $textMinutes назад"
}

fun compileHoursText(seconds: Long): String {
    val hours = seconds / 3600
    val lastCharacter = hours % 10
    val lastTwoCharacret = hours % 100

    var textHours =  when {
        (lastCharacter == 1L) -> "час"
        (lastCharacter in 2L..4L) -> "часа"
        (lastTwoCharacret in 11L..14L) -> "часов "
        else -> "часов"
    }

    return "был(а) в сети $hours $textHours назад"

}
