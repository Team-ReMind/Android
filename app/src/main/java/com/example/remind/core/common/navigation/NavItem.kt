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
        label = "오늘의 기록",
        icon_unfill = R.drawable.ic_home,
        route = Screens.Patience.Home.route
    ),
    NavItem(
        label = "무드 차트",
        icon_unfill = R.drawable.ic_moodchart,
        route = Screens.Patience.MoodChart.route
    ),
    NavItem(
        label = "약 복용",
        icon_unfill = R.drawable.ic_capsul,
        route = Screens.Patience.Medicine.route
    ),
    NavItem(
        label = "마이페이지",
        icon_unfill = R.drawable.ic_mypage,
        route = Screens.Patience.MyPage.route
    ),

)
