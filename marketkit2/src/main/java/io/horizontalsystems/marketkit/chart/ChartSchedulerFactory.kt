package io.horizontalsystems.marketkit2.chart

import io.horizontalsystems.marketkit2.models.ChartInfoKey
import io.horizontalsystems.marketkit2.chart.scheduler.ChartScheduler
import io.horizontalsystems.marketkit2.providers.HsProvider

class ChartSchedulerFactory(
    private val manager: ChartManager,
    private val provider: HsProvider,
    private val indicatorPoints: Int,
    private val retryInterval: Long = 30
) {

    fun getScheduler(key: ChartInfoKey): ChartScheduler {
        return ChartScheduler(ChartSchedulerProvider(retryInterval, key, provider, manager, indicatorPoints))
    }
}
