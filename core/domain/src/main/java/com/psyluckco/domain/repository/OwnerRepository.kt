package com.psyluckco.domain.repository

import com.psyluckco.domain.model.Owner
import kotlinx.coroutines.flow.Flow

interface OwnerRepository {
    fun getOwners(): Flow<List<Owner>>
}