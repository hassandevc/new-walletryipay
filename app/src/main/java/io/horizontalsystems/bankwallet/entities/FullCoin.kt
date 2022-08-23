package io.horizontalsystems.bankwallet.entities

import io.horizontalsystems.marketkit2.models.FullCoin
import io.horizontalsystems.marketkit2.models.Platform

val FullCoin.supportedPlatforms: List<Platform>
    get() = platforms.filter { it.coinType.isSupported }
