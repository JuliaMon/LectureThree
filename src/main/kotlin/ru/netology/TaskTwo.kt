package ru.netology

fun main() {
    val typeCard = "Мир"

    val spentMoneyCardToday: Long = 50000
    val spentMoneyCardMonth: Long = 500000
    val spentMoneyVKMonth: Long = 0

    val cardTodayInPenny: Long = (spentMoneyCardToday * 100)
    val cardMonthInPenny: Long = (spentMoneyCardMonth * 100)
    val vkMonthInPenny: Long = (spentMoneyVKMonth * 100)

    val currentPayment: Long = 50000
    val currentPaymentInPenny: Long = (currentPayment * 100)

    println(showCommission(typeCard, cardTodayInPenny, cardMonthInPenny, vkMonthInPenny, currentPaymentInPenny))

}

fun showCommission(
    typeCard: String,
    cardTodayInPenny: Long,
    cardMonthInPenny: Long,
    vkMonthInPenny: Long,
    currentPaymentInPenny: Long,
): String {

    return when (typeCard) {
        "Vk Pay" -> showVkCommission(vkMonthInPenny, currentPaymentInPenny)
        "Mastercard", "Maestro" -> showMastercardOrMaestroCommission(cardTodayInPenny, cardMonthInPenny, currentPaymentInPenny)
        "Visa", "Мир" -> showVisaOrMirCommission(currentPaymentInPenny, cardTodayInPenny, cardMonthInPenny)
        else -> "Тип карты или счета не поддерживаются"
    }
}

fun showVisaOrMirCommission(
    currentPaymentInPenny: Long,
    cardTodayInPenny: Long,
    cardMonthInPenny: Long,
): String {
    val minCommission: Long = 3500
    val commissionRate = 0.0075
    val maxPerDayInPenny: Long = 15000000
    val maxPerMonthInPenny: Long = 60000000
    val resultCommission = (currentPaymentInPenny * commissionRate).toLong()

    return when {
        (cardTodayInPenny + currentPaymentInPenny > maxPerDayInPenny) -> "Превышен суточный лимит перевода"
        (cardMonthInPenny + currentPaymentInPenny > maxPerMonthInPenny) -> "Превышен месячный лимит переводов"
        (cardTodayInPenny + currentPaymentInPenny + cardMonthInPenny > maxPerMonthInPenny) -> "Превышен месячный лимит переводов"
        (resultCommission <= minCommission) -> minCommission.toString()
        else ->  resultCommission.toString()
    }
}

fun showMastercardOrMaestroCommission(
    cardTodayInPenny: Long,
    cardMonthInPenny: Long,
    currentPaymentInPenny: Long
): String {
    val maxPerDayInPenny: Long = 15000000 // 150 000 rubles
    val maxPerMonthInPenny: Long = 60000000 // 600 000 rubles
    val commissionStart: Long = 7500000 //75 000 rubles
    val commissionResult = ((currentPaymentInPenny * 0.006) + 2000).toLong().toString()

    return when {
        (cardMonthInPenny == maxPerMonthInPenny
                || currentPaymentInPenny + cardMonthInPenny > maxPerMonthInPenny) -> "Превышен месячный лимит переводов"
        (cardTodayInPenny == maxPerDayInPenny
                || currentPaymentInPenny + cardTodayInPenny > maxPerDayInPenny) -> "Превышен суточный лимит перевода"
        (cardMonthInPenny > commissionStart || cardTodayInPenny > commissionStart) -> commissionResult
        (currentPaymentInPenny + cardMonthInPenny > commissionStart) -> commissionResult
        (currentPaymentInPenny + cardTodayInPenny > commissionStart) -> commissionResult
        (currentPaymentInPenny > commissionStart) -> commissionResult
        else -> "0"
    }
}

fun showVkCommission(vkMonthInPenny: Long, currentPaymentInPenny: Long): String {
    val maxPerMonthInPenny: Long = 4000000
    val maxPerPaymentInPenny: Long = 1500000

    return when {
        (currentPaymentInPenny > maxPerPaymentInPenny) -> "Превышен лимит единичного перевода"
        (currentPaymentInPenny + vkMonthInPenny > maxPerMonthInPenny) -> "Превышен месячный лимит переводов"
        else -> "0"
    }
}