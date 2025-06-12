package com.psyluckco.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    val id: Int,
    val name: String,
    val org: String
)
