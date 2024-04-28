package com.example.remind.core.common.navigation

import com.example.remind.R

data class NavItem(
    val label: String,
    val icon_unfill: Int,
    val route: String
)

val listOfNavItem: List<NavItem> = listOf(
    NavItem(
        label = "홈",
        icon_unfill = R.drawable.ic_example,
        route = Screens.FirstScreen.name
    ),
    NavItem(
        label = "두번째",
        icon_unfill = R.drawable.ic_example,
        route = Screens.SecondScreen.name
    ),
    NavItem(
        label = "세번째",
        icon_unfill = R.drawable.ic_example,
        route = Screens.ThirdScreen.name
    ),
    NavItem(
        label = "네번째",
        icon_unfill = R.drawable.ic_example,
        route = Screens.FourthScreen.name
    ),

)
