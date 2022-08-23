package io.horizontalsystems.marketkit2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SyncerState(
    @PrimaryKey
    val key: String,
    val value: String,
)
