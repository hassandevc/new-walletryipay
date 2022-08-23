package io.horizontalsystems.marketkit2.storage

import io.horizontalsystems.marketkit2.models.ChartInfoKey
import io.horizontalsystems.marketkit2.models.ChartPointEntity
import io.horizontalsystems.marketkit2.models.HsTimePeriod

class ChartPointStorage(marketDatabase: MarketDatabase) {
    private val chartPointDao = marketDatabase.chartPointDao()

    fun save(chartPoints: List<ChartPointEntity>) {
        chartPointDao.insert(chartPoints)
    }

    fun getList(
        coinUid: String,
        currencyCode: String,
        interval: HsTimePeriod
    ): List<ChartPointEntity> {
        return chartPointDao.getList(coinUid, currencyCode, interval.value)
    }

    fun delete(key: ChartInfoKey) {
        chartPointDao.delete(key.coin.uid, key.currencyCode, key.interval.value)
    }
}
