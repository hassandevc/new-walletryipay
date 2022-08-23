package io.horizontalsystems.bankwallet.entities

import io.horizontalsystems.marketkit2.models.CoinType

data class InitialSyncSetting(val coinType: CoinType,
                              var syncMode: SyncMode)
