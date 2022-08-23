package io.horizontalsystems.marketkit2.models

class ChartInfo (
    val points: List<ChartPoint>,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val isExpired: Boolean
)
