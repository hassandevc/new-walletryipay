package io.horizontalsystems.marketkit2.managers

import io.horizontalsystems.marketkit2.ProviderError
import io.horizontalsystems.marketkit2.providers.HsProvider
import io.horizontalsystems.marketkit2.storage.CoinHistoricalPriceStorage
import io.reactivex.Single
import java.math.BigDecimal
import kotlin.math.abs

class CoinHistoricalPriceManager(
    private val storage: CoinHistoricalPriceStorage,
    private val hsProvider: HsProvider,
) {

    fun coinHistoricalPriceSingle(
        coinUid: String,
        currencyCode: String,
        timestamp: Long
    ): Single<BigDecimal> {

        storage.coinPrice(coinUid, currencyCode, timestamp)?.let {
            return Single.just(it.value)
        }

        return hsProvider.historicalCoinPriceSingle(coinUid, currencyCode, timestamp)
            .flatMap { response ->
                if (abs(timestamp - response.timestamp) < 24 * 60 * 60) {
                    Single.just(response.price)
                } else {
                    Single.error(ProviderError.ReturnedTimestampIsVeryInaccurate())
                }
            }
    }

    fun coinHistoricalPrice(coinUid: String, currencyCode: String, timestamp: Long): BigDecimal? {
        return storage.coinPrice(coinUid, currencyCode, timestamp)?.value
    }

}
