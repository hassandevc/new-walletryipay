package io.horizontalsystems.marketkit2.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CoinPriceResponse(
    val uid: String,
    val price: BigDecimal?,
    @SerializedName("price_change_24h")
    val priceChange: BigDecimal?,
    @SerializedName("last_updated")
    val lastUpdated: Long?
) {
    fun coinPrice(currencyCode: String) = when {
        price == null || priceChange == null || lastUpdated == null -> null
        else -> CoinPrice(uid, currencyCode, price, priceChange, lastUpdated)
    }
}
