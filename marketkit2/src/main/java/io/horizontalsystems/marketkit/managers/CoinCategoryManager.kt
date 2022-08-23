package io.horizontalsystems.marketkit2.managers

import io.horizontalsystems.marketkit2.models.CoinCategory
import io.horizontalsystems.marketkit2.storage.CoinCategoryStorage
import io.reactivex.subjects.PublishSubject

class CoinCategoryManager(
    private val storage: CoinCategoryStorage
) {
    val coinCategoriesObservable = PublishSubject.create<List<CoinCategory>>()

    fun coinCategories(): List<CoinCategory> {
        return storage.coinCategories()
    }

    fun coinCategories(uids: List<String>): List<CoinCategory> {
        return storage.coinCategories(uids)
    }

    fun coinCategory(uid: String): CoinCategory? {
        return storage.coinCategory(uid)
    }

    fun handleFetched(coinCategories: List<CoinCategory>) {
        storage.save(coinCategories)
        coinCategoriesObservable.onNext(coinCategories)
    }
}
