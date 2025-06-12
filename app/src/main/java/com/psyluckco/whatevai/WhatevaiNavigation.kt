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
}

object WhatevaiDestinations {
    const val HOME_ROUTE = WhatevaiScreens.HOME_SCREEN
    const val CHAT_ROUTE = WhatevaiScreens.CHAT_SCREEN
}

class WhatevaiNavigationActions(private val navController: NavHostController) {

    fun navigateToChat() {
        navController.navigate(WhatevaiScreens.CHAT_SCREEN)
    }

}