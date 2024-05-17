package com.example.remind.feature.screens.patience.home

import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(): BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>(
    initialState = HomeContract.State()
) {
    override fun reduceState(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.WritingButtonClicked -> {
                navigateToWriting()
            }
        }
    }

    fun navigateToWriting() {
        postEffect(
            HomeContract.Effect.NavigateTo(
                destinaton = Screens.Patience.Home.WritingMoodStep1.route,
                navOptions = navOptions {
                    popUpTo(Screens.Patience.Home.route) {
                        inclusive = false
                    }
                }
            )
        )
    }
}