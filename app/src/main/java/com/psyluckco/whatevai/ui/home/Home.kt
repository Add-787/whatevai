/**
 * Created by developer on 30-05-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.whatevai.ui.home

import android.R
import android.R.attr.strokeWidth
import android.R.attr.text
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.psyluckco.domain.model.Device
import com.psyluckco.whatevai.ui.theme.WhatevaiTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlin.math.min

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val homeScreenUiState by viewModel.state.collectAsStateWithLifecycle()
    val uiState = homeScreenUiState
    Box {
        
        if(uiState.errorMessage != null) {
            HomeScreenError(onRetry = {})
        } else {
            HomeScreenReady(
                devices = uiState.devices
            )
        }
    }
}

@Composable
fun HomeScreenReady(
    modifier: Modifier = Modifier,
    devices: PersistentList<Device> = persistentListOf(),
    onDeviceClicked: (Device) -> Unit = {}
) {
    Surface(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1)
        ) {
            items(devices) {
                DeviceCard(
                    deviceName = it.name,
                    ownerName = it.ownerId?.toString()?: "Unknown",
                    percentage = it.oxygenPerc
                )

            }

        }
    }
}

@Composable
fun DeviceCard(
    modifier: Modifier = Modifier,
    deviceName: String,
    ownerName: String,
    percentage: Float,
    startAngle: Float = -90f,
//    backgroundArcColor: Color? = Color(Color.LTGRAY)
) {
    Card(
        modifier = modifier
            .height(90.dp)
            .fillMaxWidth(),
        shape = RectangleShape
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Filled.Settings,
                    modifier = Modifier.size(60.dp),
                    contentDescription = null
                )

            }

            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = deviceName, style = MaterialTheme.typography.headlineMedium)
                Text(text = ownerName, style = MaterialTheme.typography.headlineSmall)
            }

            Spacer(modifier = Modifier.width(60.dp))

            val primary = MaterialTheme.colorScheme.primary
            
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp),
                contentAlignment = Alignment.Center
            ) {
                val clampedPercentage = percentage.coerceIn(0f, 100f)
                val sweepAngle = (clampedPercentage / 100f) * 360f

                val animatedSweepAngle by animateFloatAsState(
                    targetValue = sweepAngle,
                    animationSpec = spring(
                        dampingRatio = 0.5f,
                        stiffness = 100f
                    ),
                    label = "sweepAngle"
                )

                Canvas(modifier = modifier.fillMaxHeight()) {
                    val diameter = min(size.width, size.height)
                    val arcSize = Size(diameter - 10.0f, diameter - 10.0f)
                    val topLeft = Offset(
                        x = -70.0f,
                        y = 50.0f
                    )

                    // drawCircle(color = primary, radius = 10.0f)

                    // Draw the track if color is specified
                    drawArc(
                        color = primary.copy(alpha = 0.4f),
                        startAngle = startAngle,
                        sweepAngle = 360f,
                        useCenter = false,
                        topLeft = topLeft,
                        size = Size(width = 150.0f, height = 150.0f),
                        style = Stroke(width = 40.0f, cap = StrokeCap.Round)
                    )


                    // Draw the main percentage arc
                    drawArc(
                        color = primary,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = topLeft,
                        size = Size(width = 150.0f, height = 150.0f),
                        style = Stroke(width = 40.0f, cap = StrokeCap.Round)
                    )

                }

                Text(text = "$percentage%", style = MaterialTheme.typography.labelSmall)

            }

        }
    }
}


@Preview
@Composable
private fun DeviceCardPreview() {
    WhatevaiTheme {
        DeviceCard(
            deviceName = "Athena",
            ownerName = "Tismo",
            percentage = 60f
        )
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun HomeScreenReadyPreview(
    modifier: Modifier = Modifier
) {
    WhatevaiTheme {
        HomeScreenReady(
            devices = persistentListOf(
                Device(
                    id = 1,
                    name = "Athena",
                    status = true,
                    oxygenPerc = 60f
                ),
                Device(
                    id = 2,
                    name = "Aros",
                    status = false,
                    oxygenPerc = 60f
                ),
                Device(
                    id = 3,
                    name = "Hephaestes",
                    status = true,
                    oxygenPerc = 60f
                ),
                Device(
                    id = 4,
                    name = "Kronos",
                    status = true,
                    oxygenPerc = 60f
                )
            )
        )
    }
}


@Composable
fun HomeScreenError(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "An error has occurred."
            )

        }

    }
}

@Preview
@Composable
private fun HomeErrorScreenPreview() {
    WhatevaiTheme {
        HomeScreenError(onRetry = { })
    }
}