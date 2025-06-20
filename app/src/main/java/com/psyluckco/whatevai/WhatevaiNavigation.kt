/**
 * Created by developer on 12-06-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.whatevai

import androidx.navigation.NavHostController

private object WhatevaiScreens {
    const val HOME_SCREEN = "home"
    const val CHAT_SCREEN = "chat"
    const val DETAIL_SCREEN = "detail"
}

object WhatevaiDestinations {
    const val HOME_ROUTE = WhatevaiScreens.HOME_SCREEN
    const val CHAT_ROUTE = WhatevaiScreens.CHAT_SCREEN
    const val DETAIL_ROUTE = WhatevaiScreens.DETAIL_SCREEN
}

class WhatevaiNavigationActions(private val navController: NavHostController) {

    fun navigateToChat() {
        navController.navigate(WhatevaiScreens.CHAT_SCREEN)
    }

    fun navigateToDetail() {
        navController.navigate(WhatevaiScreens.DETAIL_SCREEN)
    }

    fun navigateToHome() {
        navController.navigate(WhatevaiScreens.HOME_SCREEN)

    }

}