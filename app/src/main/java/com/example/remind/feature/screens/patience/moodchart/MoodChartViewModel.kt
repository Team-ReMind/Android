package com.example.remind.feature.screens.patience.moodchart

import androidx.lifecycle.viewModelScope
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.domain.usecase.patience_usecase.GetFeelingActivityUseCase
import com.example.remind.domain.usecase.patience_usecase.GetFeelingPercentUseCase
import com.example.remind.domain.usecase.patience_usecase.GetMoodChartUseCase
import com.example.remind.domain.usecase.patience_usecase.GetMoodDailyUseCase
import com.example.remind.domain.usecase.patience_usecase.PatientMedicineDailyUseCase
import com.example.remind.domain.usecase.patience_usecase.SeriesRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MoodChartViewModel @Inject constructor(
    private val getMoodChartUseCase: GetMoodChartUseCase,
    private val getFeelingPercentUseCase: GetFeelingPercentUseCase,
    private val getFeelingActivityUseCase: GetFeelingActivityUseCase,
    private val getMoodDailyUseCase: GetMoodDailyUseCase,
    private val patientMedicineDailyUseCase: PatientMedicineDailyUseCase,
    private val seriesRecordUseCase: SeriesRecordUseCase
): BaseViewModel<MoodChartContract.Event, MoodChartContract.State, MoodChartContract.Effect>(
    initialState = MoodChartContract.State()
) {
    val year = LocalDate.now().year
    val month = LocalDate.now().monthValue
    val date = LocalDate.now().dayOfMonth
    init {
        viewModelScope.launch {
            getMoodChartData(year, month, date-3)
            getFeelingPerCent()
            getSeriesRecord()

        }
    }
    override fun reduceState(event: MoodChartContract.Event) {
        when(event) {
            is MoodChartContract.Event.LoadingNextData -> {
                getMoodChartData(event.year, event.month, event.day)
            }
            is MoodChartContract.Event.storeDate -> {
                updateState(currentState.copy(date = event.date))
            }
            is MoodChartContract.Event.ClickToBottomSheet -> {
                getDailyMood(event.moodDate)
                getDailyMedicine(event.moodDate)
            }
            else ->{}
        }
    }

    private fun getMoodChartData(year: Int, month: Int, day: Int) {
        viewModelScope.launch {
            val result = getMoodChartUseCase.invoke(year, month, day, 7)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(
                        moodChartData = result.data.data,
                        xAxisData = result.data.data.moodChartDtos.map { "${it.day}일" },
                        yAxisData = result.data.data.moodChartDtos.map { it.score }
                    ))
                }
                else -> {}
            }
        }
    }
    private fun getFeelingPerCent() {
        viewModelScope.launch {
            val result = getFeelingPercentUseCase.invoke()
            when(result) {
                is ApiResult.Success -> {
                    if(result.data.data != null) {
                        updateState(currentState.copy(
                            feelingTotalPerCent = result.data.data
                        ))
                    }
                }
                else ->{}
            }
        }
    }
    private fun getDailyMood(moodDate: String) {
        viewModelScope.launch {
            val result = getMoodDailyUseCase.invoke(moodDate)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(
                        dailyMood = result.data.data
                    ))
                }
                else -> {}
            }
        }
    }
    private fun getDailyMedicine(date: String) {
        viewModelScope.launch {
            val result = patientMedicineDailyUseCase.invoke(0,date)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(
                        dailyMedicine = result.data.data.dailyTakingMedicineDtos
                    ))
                }
                else -> {}
            }
        }
    }

    private fun getSeriesRecord() {
        viewModelScope.launch {
            val result = seriesRecordUseCase.invoke()
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(currentSeriesDay = result.data.data.currentSeriesDays))
                }
                else -> {}
            }
        }
    }



    fun setToastMessage(text: String) {
        postEffect(MoodChartContract.Effect.Toastmessage(text))
    }
}