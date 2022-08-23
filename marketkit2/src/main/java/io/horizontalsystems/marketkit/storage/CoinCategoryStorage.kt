package io.horizontalsystems.marketkit2.storage

import io.horizontalsystems.marketkit2.models.CoinCategory

class CoinCategoryStorage(marketDatabase: MarketDatabase) {
    private val coinCategoryDao = marketDatabase.coinCategoryDao()

    fun coinCategories(): List<CoinCategory> {
        return coinCategoryDao.getCoinCategories()
    }

    fun coinCategories(uids: List<String>): List<CoinCategory> {
        return coinCategoryDao.getCoinCategories(uids)
    }

    fun coinCategory(uid: String): CoinCategory? {
        return coinCategoryDao.getCoinCategories(listOf(uid)).firstOrNull()
    }

    fun save(coinCategories: List<CoinCategory>) {
        coinCategoryDao.insert(coinCategories)
    }
}
