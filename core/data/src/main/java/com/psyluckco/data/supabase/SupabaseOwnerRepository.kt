/**
 * Created by developer on 12-06-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.data.supabase

import com.psyluckco.domain.model.Owner
import com.psyluckco.domain.repository.OwnerRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SupabaseOwnerRepository @Inject constructor(
    val postgrest: Postgrest
) : OwnerRepository {

    override fun getOwners(): Flow<List<Owner>> = flow {
        try {
            val owners = postgrest["Owners"].select().decodeList<Owner>()
            emit(owners)
        } catch(e: Exception) {
            e.printStackTrace()
        }

    }

}