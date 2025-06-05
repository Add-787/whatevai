/**
 * Created by developer on 30-05-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.whatevai.ui.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psyluckco.domain.model.Device
import com.psyluckco.domain.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    deviceRepository: DeviceRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeScreenUiState())

    private val refreshing = MutableStateFlow(false)

    private val featuredDevices = deviceRepository.getDevices()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    val state: StateFlow<HomeScreenUiState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                refreshing,
                featuredDevices
            ) {
                refreshing, devices ->
                        HomeScreenUiState(
                            isLoading = refreshing,
                            devices = devices.toPersistentList()
                        )

            }.catch {
                throwable ->
                HomeScreenUiState(
                    isLoading = false,
                    errorMessage = throwable.message
                )
            }.collect {
                _state.value = it
            }

        }
    }

}

@Immutable
sealed interface HomeAction {
    data class OnDeviceClicked(val device: Device): HomeAction
}

@Immutable
data class HomeScreenUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val devices: PersistentList<Device> = persistentListOf<Device>()
)