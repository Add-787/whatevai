package com.psyluckco.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Serializable
data class Device(
    val id: Int,
//    @Contextual
//    @SerialName("created_at")
//    val createdAt: OffsetDateTime = OffsetDateTime.MIN,
    val name: String,
    val status: Boolean,

    @SerialName("oxygen_perc")
    val oxygenPerc: Float,
    @SerialName("owner_id")
    val ownerId: Int? = null
)
