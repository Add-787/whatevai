/**
 * Created by developer on 29-05-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.domain.repository

import com.psyluckco.domain.model.Device
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {

    fun getDevices(): Flow<List<Device>>
}