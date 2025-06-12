/**
 * Created by developer on 29-05-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.data.supabase

import com.psyluckco.domain.model.Device
import com.psyluckco.domain.repository.DeviceRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SupabaseDeviceRepository @Inject constructor(
    val postgrest: Postgrest
) : DeviceRepository {

    override fun getDevices(): Flow<List<Device>> = flow {
        try {
            val devices = postgrest["Devices"].select().decodeList<Device>()
            emit(devices)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}