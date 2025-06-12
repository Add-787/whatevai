package com.psyluckco.data

import com.psyluckco.data.supabase.SupabaseDeviceRepository
import com.psyluckco.data.supabase.SupabaseOwnerRepository
import com.psyluckco.domain.repository.DeviceRepository
import com.psyluckco.domain.repository.OwnerRepository
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class SupabaseIntegrationTest {

    private lateinit var postgrest: Postgrest
    private lateinit var deviceRepository: DeviceRepository
    private lateinit var ownerRepository: OwnerRepository

    private val supabaseUrl = BuildConfig.SUPABASE_URL
    private val supabaseKey = BuildConfig.SUPABASE_KEY

    @Before
    fun setup() {

        val client = createSupabaseClient(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ) {
            install(Postgrest)
        }

        postgrest = client.postgrest
        deviceRepository = SupabaseDeviceRepository(postgrest)
        ownerRepository = SupabaseOwnerRepository(postgrest)

    }

    @Test
    fun getDevicesFromSupabaseDatabaseTest() = runBlocking {

        try {
            val devicesFlow = deviceRepository.getDevices()
            val devices = devicesFlow.first()

            assertTrue(!devices.isEmpty())
            println("Fetched ${devices.size} devices.")

        } catch (e: Exception) {
            Assert.fail("Failed to fetch devices from Supabase: ${e.message}")
        }

    }

    @Test
    fun getOwnersFromSupabaseDatabaseTest() = runBlocking {
        try {
            val ownersFlow = ownerRepository.getOwners().collect {
                owners ->
                    assertTrue(!owners.isEmpty())
                    println("Fetched ${owners.size} owners.")
            }

        } catch(e: Exception) {
            Assert.fail("Failed to fetch owners from Supabase: ${e.message}")
        }
    }
}