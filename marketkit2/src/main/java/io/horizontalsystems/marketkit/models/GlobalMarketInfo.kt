package io.horizontalsystems.marketkit2.models

import androidx.room.Entity

@Entity(primaryKeys = ["currencyCode", "timePeriod"])
class GlobalMarketInfo(
    val currencyCode: String,
    val timePeriod: HsTimePeriod,
    val points: List<GlobalMarketPoint>,
    val timestamp: Long
)
