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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.psyluckco.whatevai.ui.home.HomeScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = WhatevaiDestinations.HOME_ROUTE,
    navActions: WhatevaiNavigationActions = remember(navController) {
        WhatevaiNavigationActions(navController)
    },
    modifier: Modifier = Modifier
) {

    val currentNavBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            WhatevaiDestinations.HOME_ROUTE
        ) {
            HomeScreen()
        }

        composable(
            WhatevaiDestinations.CHAT_ROUTE
        ) {
            // ChatScreen()
        }

    }


}