package com.example.remind.feature.screens.patience.moodchart

import androidx.lifecycle.viewModelScope
import com.example.remind.core.base.BaseViewModel
import com.example.remind.data.model.response.MoodChartDto
import com.example.remind.data.network.adapter.ApiResult
import com.example.remind.domain.usecase.patience_usecase.GetMoodChartUseCase
import com.example.remind.feature.screens.patience.home.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MoodChartViewModel @Inject constructor(
    private val getMoodChartUseCase: GetMoodChartUseCase
): BaseViewModel<MoodChartContract.Event, MoodChartContract.State, MoodChartContract.Effect>(
    initialState = MoodChartContract.State()
) {
    val year = LocalDate.now().year
    val month = LocalDate.now().monthValue
    val date = LocalDate.now().dayOfMonth
    init {
        viewModelScope.launch {
            getMoodChartData(year, month, date-3)
            extractList()
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
            else ->{}
        }
    }

    private fun getMoodChartData(year: Int, month: Int, day: Int) {
        viewModelScope.launch {
            val result = getMoodChartUseCase.invoke(year, month, day, 7)
            when(result) {
                is ApiResult.Success -> {
                    updateState(currentState.copy(
                        moodChartData = result.data.data
                    ))
                }
                else -> {}
            }
        }
    }
    fun extractList() {
        val size = uiState.value.moodChartData.moodChartDtos.size
        val list: MutableList<String> = mutableListOf<String>()
        for(i in 0..size-1) {
            val data = formattingDate(uiState.value.moodChartData.moodChartDtos.get(i).localDate)
            list.add("${data}일")
        }
        updateState(currentState.copy(xAxisData = list))
    }
    fun formattingDate(dateString: String):Int {
        val extractString = dateString.takeLast(2)
        val date: Int = extractString.toInt()
        return date
    }


    fun setToastMessage(text: String) {
        postEffect(MoodChartContract.Effect.Toastmessage(text))
    }
}