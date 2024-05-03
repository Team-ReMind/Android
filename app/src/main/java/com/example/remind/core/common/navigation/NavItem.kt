package com.example.remind.core.common.navigation

import com.example.remind.R
import com.example.remind.app.Screens

data class NavItem(
    val label: String,
    val icon_unfill: Int,
    val route: String
)

val listOfNavItem: List<NavItem> = listOf(
    NavItem(
        label = "홈",
        icon_unfill = R.drawable.ic_example,
        route = Screens.Patience.Fitst.route
    ),
    NavItem(
        label = "두번째",
        icon_unfill = R.drawable.ic_example,
        route = Screens.Patience.Second.route
    ),
    NavItem(
        label = "세번째",
        icon_unfill = R.drawable.ic_example,
        route = Screens.Patience.Third.route
    ),
    NavItem(
        label = "네번째",
        icon_unfill = R.drawable.ic_example,
        route = Screens.Patience.Fourth.route
    ),

)
