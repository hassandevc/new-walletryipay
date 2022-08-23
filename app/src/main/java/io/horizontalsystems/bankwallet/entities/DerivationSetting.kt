package io.horizontalsystems.bankwallet.entities

import io.horizontalsystems.bankwallet.entities.AccountType.Derivation
import io.horizontalsystems.marketkit2.models.CoinType

class DerivationSetting(val coinType: CoinType,
                        var derivation: Derivation)
