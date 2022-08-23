package io.horizontalsystems.marketkit2.models

data class ChartInfoKey(
        val coin: Coin,
        val currencyCode: String,
        val interval: HsTimePeriod
)
