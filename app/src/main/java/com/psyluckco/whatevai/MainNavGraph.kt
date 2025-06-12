/**
 * Created by developer on 12-06-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.whatevai

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "",
    navActions: WhatevaiNavigationActions = remember(navController) {
        WhatevaiNavigationActions(navController)
    },
    modifier: Modifier = Modifier
) {


}