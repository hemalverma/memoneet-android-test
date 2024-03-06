package com.hvx.memoneet.routing

enum class Screen {
    HOME,
    CAPSULE,
}
sealed class AppRouting(val route: String) {
    data object Home : AppRouting(Screen.HOME.name)
    data object Capsule : AppRouting(Screen.CAPSULE.name)
}