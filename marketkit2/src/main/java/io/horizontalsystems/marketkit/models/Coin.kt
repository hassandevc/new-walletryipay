package io.horizontalsystems.marketkit2.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Coin(
    @PrimaryKey
    val uid: String,
    val name: String,
    val code: String,
    val marketCapRank: Int? = null,
    val coinGeckoId: String? = null
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        return other is Coin && other.uid == uid
    }

    override fun hashCode(): Int {
        return uid.hashCode()
    }

    override fun toString(): String {
        return "Coin [uid: $uid; name: $name; code: $code; marketCapRank: $marketCapRank; coinGeckoId: $coinGeckoId]"
    }
}
