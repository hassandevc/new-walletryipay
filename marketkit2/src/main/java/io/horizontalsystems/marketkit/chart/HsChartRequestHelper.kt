package io.horizontalsystems.marketkit2.chart

import io.horizontalsystems.marketkit2.models.HsPointTimePeriod
import io.horizontalsystems.marketkit2.models.HsTimePeriod

object HsChartRequestHelper {

    fun pointInterval(interval: HsTimePeriod): HsPointTimePeriod {
        return when (interval) {
            HsTimePeriod.Day1 -> HsPointTimePeriod.Minute30
            HsTimePeriod.Week1 -> HsPointTimePeriod.Hour4
            HsTimePeriod.Week2 -> HsPointTimePeriod.Hour8
            else -> HsPointTimePeriod.Day1
        }
    }

    fun fromTimestamp(timestamp: Long, interval: HsTimePeriod, indicatorPoints: Int) : Long {
        // time needed for build indicators
        val pointInterval = pointInterval(interval)
        val additionalTime = indicatorPoints * pointInterval.interval

        return timestamp - interval.range - additionalTime
    }
}