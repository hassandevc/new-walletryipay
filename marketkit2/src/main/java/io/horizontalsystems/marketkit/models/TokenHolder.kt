package io.horizontalsystems.marketkit2.models

import java.math.BigDecimal

data class TokenHolder(
    val address: String,
    val share: BigDecimal
)
