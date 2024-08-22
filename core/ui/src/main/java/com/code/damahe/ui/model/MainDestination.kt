package com.code.damahe.ui.model

import com.code.damahe.res.icon.DCodeResource

data class MainDestination(
    val route: String,
    val selectedIcon: DCodeResource,
    val unselectedIcon: DCodeResource,
    val iconTextId: Int
)
