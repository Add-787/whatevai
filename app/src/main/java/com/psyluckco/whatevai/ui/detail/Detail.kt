/**
 * Created by developer on 18-06-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.whatevai.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.psyluckco.whatevai.ui.theme.WhatevaiTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {

    val uiState = DetailScreenUiState()

    Scaffold() {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

        }

    }
}

@Composable
fun DeviceScreenReady(
    modifier: Modifier = Modifier,
    dataPoints: PersistentList<Float> = persistentListOf(),
) {
    Column() {

    }

}

@Composable
fun GraphSection(
    modifier: Modifier = Modifier.height(100.dp)
) {

    val modelProducer = remember { CartesianChartModelProducer() }

    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            lineSeries { series(13,8,7,12,0,1,9,15,6) }
        }
    }

    Card {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CartesianChartHost(
                chart = rememberCartesianChart(
                    rememberLineCartesianLayer(),
                    startAxis = VerticalAxis.rememberStart(),
                    bottomAxis = HorizontalAxis.rememberBottom(),
                ),
                modelProducer = modelProducer,
                modifier = modifier
            )

        }
    }
    
}

@Preview
@Composable
private fun GraphSectionPreview() {
    WhatevaiTheme { 
        GraphSection()
    }
}

