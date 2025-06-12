package com.psyluckco.data.supabase

import com.psyluckco.data.BuildConfig
import com.psyluckco.domain.repository.DeviceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {

        return createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Postgrest)
        }
    }

    @Provides
    @Singleton
    fun providePostgrest(supabaseClient: SupabaseClient): Postgrest {
        return supabaseClient.postgrest
    }

    @Provides
    @Singleton
    fun provideDeviceRepository(postgrest: Postgrest): DeviceRepository {
        return SupabaseDeviceRepository(postgrest)
    }
}