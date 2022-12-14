package io.horizontalsystems.marketkit2.managers

import io.horizontalsystems.marketkit2.models.Post
import io.horizontalsystems.marketkit2.providers.CryptoCompareProvider
import io.reactivex.Single

class PostManager(
    private val provider: CryptoCompareProvider
) {
    fun postsSingle(): Single<List<Post>> {
        return provider.postsSingle()
    }
}
