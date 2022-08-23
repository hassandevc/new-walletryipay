package io.horizontalsystems.marketkit2.providers

import io.horizontalsystems.marketkit2.managers.CoinPriceManager
import io.horizontalsystems.marketkit2.managers.ICoinPriceCoinUidDataSource
import io.horizontalsystems.marketkit2.Scheduler

class CoinPriceSchedulerFactory(
    private val manager: CoinPriceManager,
    private val provider: HsProvider
) {
    fun scheduler(currencyCode: String, coinUidDataSource: ICoinPriceCoinUidDataSource): Scheduler {
        val schedulerProvider = CoinPriceSchedulerProvider(currencyCode, manager, provider)
        schedulerProvider.dataSource = coinUidDataSource
        return Scheduler(schedulerProvider)
    }
}
