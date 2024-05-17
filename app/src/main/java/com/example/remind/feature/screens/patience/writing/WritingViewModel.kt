package com.example.remind.feature.screens.patience.writing

import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import javax.inject.Inject

class WritingViewModel @Inject constructor(

): BaseViewModel<WritingContract.Event, WritingContract.State, WritingContract.Effect>(
    initialState = WritingContract.State()
) {
    override fun reduceState(event: WritingContract.Event) {
        when(event) {
            is WritingContract.Event.FeelingButtonClicked -> {
                updateState(currentState.copy(selectFeelingType = event.feelingType))
            }
            is WritingContract.Event.NextButtonClicked -> {
                navigateToRoute(event.destinationRoute, event.currentRoute)
            }
            is WritingContract.Event.NavigateToHome -> {
                navigateToHome()
            }
            is WritingContract.Event.PreviousButtonClicked -> {
                navigateToRoute(event.destinationRoute, event.currentRoute)
            }
        }
    }

    fun navigateToRoute(destination: String, current: String) {
        postEffect(
            WritingContract.Effect.NavigateTo(
                destinaton = destination,
                navOptions = navOptions {
                    popUpTo(current) {
                        inclusive = false
                    }
                }
            )
        )
    }

    fun navigateToHome() {
        postEffect(
            WritingContract.Effect.NavigateTo(
                destinaton = Screens.Patience.Home.route,
                navOptions = navOptions {
                    popUpTo(Screens.Patience.Home.WritingMoodStep1.route) {
                        inclusive = true
                    }
                }
            )
        )
    }



}