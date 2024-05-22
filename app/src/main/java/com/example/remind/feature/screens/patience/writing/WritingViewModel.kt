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
            //step1에서 이모티콘 눌렀을때
            is WritingContract.Event.FeelingButtonClicked -> {
                updateState(currentState.copy(
                    writingMoodRequest = currentState.writingMoodRequest.copy(
                        feelingType = event.feelingType
                    )
                ))
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
            //step 2 활동을 선택하는 화면에서 아이콘 누르는 기능
            is WritingContract.Event.ActivityButtonClicked -> {
                updateState(currentState.copy(
                    moodActivity = currentState.moodActivity.copy(
                        activityId = event.activityId
                    )
                ))
            }

            //step2 활동별 무드 등록하는 step2feelingscreen
            is WritingContract.Event.FeelingActivityButtonClicked -> {
                updateState(currentState.copy(
                    moodActivity = currentState.moodActivity.copy(
                        feelingType = event.feelingType
                    )
                ))
            }
            //활동기록 마지막 조회 페이지 가기 전 feelingscreen
            is WritingContract.Event.StoreFeelingListItem -> {
                updateState(currentState.copy(
                    moodActivity = currentState.moodActivity.copy(
                        detail = event.detail
                    )
                ))
                val newData = currentState.moodActivity
                updateState(currentState.copy(
                    writingMoodRequest = currentState.writingMoodRequest.copy(
                        moodActivities = currentState.writingMoodRequest.moodActivities + newData
                    )
                ))
                //다시 초기화
                updateState(currentState.copy(
                    moodActivity = MoodActivity()
                ))
                navigateToRoute(Screens.Patience.Home.WritingMoodStep2Last.route, Screens.Patience.Home.WritingMoodStep2Feeling.route, false)
            }
            is WritingContract.Event.NavigateToStep2 -> {
                navigateToRoute(event.destinationRoute, event.currentRoute, true)
            }
            is WritingContract.Event.SendInfoButton -> {
                updateState(currentState.copy(
                    writingMoodRequest = currentState.writingMoodRequest.copy(
                        localDate = event.localDate,
                        detail = event.detail
                    )
                ))
                setMoodData(currentState.writingMoodRequest)
                navigateToRoute(event.destinationRoute, event.currentRoute,true)
                updateState(currentState.copy(
                    writingMoodRequest = WritingMoodRequest()
                ))
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