package io.horizontalsystems.marketkit2.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class MarketInfoTvlResponse(
    @SerializedName("date")
    val timestamp: Long,
    val tvl: BigDecimal?
)
