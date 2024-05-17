package com.example.remind.feature.screens.patience.writing

import androidx.compose.ui.res.stringResource
import androidx.navigation.navOptions
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.model.FeelingScoreModel
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



}