package com.psyluckco.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class Device(
    val id: Int,
    val createdAt: OffsetDateTime = OffsetDateTime.MIN,
    val name: String,
    val status: Boolean,
    val ownerId: Int? = null
)
