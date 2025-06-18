/**
 * Created by developer on 18-06-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.whatevai.ui.detail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : ViewModel() {

}

@Immutable
sealed interface DetailAction {
    object OnBackClicked: DetailAction
    data class OnQuerySent(val query: String): DetailAction
}

@Immutable
data class DetailScreenUiState(
    val dataPoints: PersistentList<Float> = persistentListOf()
)