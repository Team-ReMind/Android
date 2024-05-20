package com.example.remind.feature.screens.patience.writing

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.example.remind.app.Screens
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.model.request.MoodActivity
import com.example.remind.data.model.request.WritingMoodRequest
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.domain.usecase.patience_usecase.GetActivityUseCase
import com.example.remind.domain.usecase.patience_usecase.SetTodayMoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WritingViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val setTodayMoodUseCase: SetTodayMoodUseCase
): BaseViewModel<WritingContract.Event, WritingContract.State, WritingContract.Effect>(
    initialState = WritingContract.State()
) {
    init {
        getActivityList()

    }
    override fun reduceState(event: WritingContract.Event) {
        when(event) {
            is WritingContract.Event.FeelingButtonClicked -> {
                updateState(currentState.copy(totalFeelingType = event.feelingType))
            }
            is WritingContract.Event.NextButtonClicked -> {
                navigateToRoute(event.destinationRoute, event.currentRoute, false)
            }
            is WritingContract.Event.NavigateToHome -> {
                navigateToRoute(Screens.Patience.Home.route, Screens.Patience.Home.WritingMoodStep1.route, true)
            }
            is WritingContract.Event.PreviousButtonClicked -> {
                navigateToRoute(event.destinationRoute, event.currentRoute, true)
            }
            is WritingContract.Event.ActivityButtonClicked -> {
                updateState(currentState.copy(activityId = event.activityId))
                updateState(currentState.copy(activityName = event.activityName))
            }
//
            is WritingContract.Event.FeelingActivityButtonClicked -> {
                updateState(currentState.copy(feelingType = event.feelingType))
            }
            is WritingContract.Event.StoreFeelingListItem -> {
                val newList = currentState.MoodActivityList + MoodActivity(
                    event.activityData.activityId,
                    event.activityData.detail,
                    event.activityData.feelingType
                )
                navigateToRoute(event.destinationRoute, event.currentRoute, false)
            }
            is WritingContract.Event.NavigateToStep2 -> {
                navigateToRoute(event.destinationRoute, event.currentRoute, true)
            }
            is WritingContract.Event.SendInfoButton -> {
                setMoodData(
                    WritingMoodRequest(
                        detail = event.detail,
                        feelingType = currentState.totalFeelingType,
                        localDate = event.localDate,
                        moodActivities = currentState.MoodActivityList
                    )
                )
                navigateToRoute(event.destinationRoute, event.currentRoute,true)
            }
        }
    }

    fun navigateToRoute(destination: String, current: String, inclusiveData: Boolean) {
        postEffect(
            WritingContract.Effect.NavigateTo(
                destinaton = destination,
                navOptions = navOptions {
                    popUpTo(current) {
                        inclusive = inclusiveData
                    }
                }
            )
        )
    }

    private fun getActivityList() {
        viewModelScope.launch {
            val result = getActivityUseCase.invoke()
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(ActivityList = result.data.data.activities))
                }
                is ApiResult.Failure.UnknownApiError -> {
                    setToastMessage("리마인드 고객센터에 문의하세요")
                }
                is ApiResult.Failure.NetworkError -> {
                    setToastMessage("네트워크 설정을 확인해주세요")
                }
                is ApiResult.Failure.HttpError -> {
                    setToastMessage("api 응답에러 ${result.code}")
                }
            }
        }
    }

    private fun setMoodData(body: WritingMoodRequest) {
        viewModelScope.launch {
           val result = setTodayMoodUseCase.invoke(body)
            when(result) {
                is ApiResult.Success -> {

                }
                is ApiResult.Failure.UnknownApiError -> {
                    setToastMessage("리마인드 고객센터에 문의하세요")
                }
                is ApiResult.Failure.NetworkError -> {
                    setToastMessage("네트워크 설정을 확인해주세요")
                }
                is ApiResult.Failure.HttpError -> {
                    setToastMessage("api 응답에러 ${result.code}")
                }
            }
        }
    }

    fun setToastMessage(text: String) {
        postEffect(WritingContract.Effect.Toastmessage(text))
    }




}