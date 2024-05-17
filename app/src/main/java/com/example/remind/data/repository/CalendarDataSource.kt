package com.example.remind.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.remind.data.model.CalendarUiModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.WeekFields
import java.util.Locale
import java.util.stream.Collectors
import java.util.stream.Stream

class CalendarDataSource {
    val today: LocalDate
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            return LocalDate.now()
        }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
        val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
        val endDayOfWeek = firstDayOfWeek.plusDays(7)
        val visibleDates = getDateBetween(firstDayOfWeek,endDayOfWeek)
        return toUiModel(visibleDates, lastSelectedDate)
    }

    private fun getDateBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return Stream.iterate(startDate) { date ->
            date.plusDays(1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }

     fun getWeekly(date: LocalDate): String {
        val weekFields = WeekFields.of(Locale.getDefault())
        val weekOfYear = date.get(weekFields.weekOfMonth())
        val month = date.monthValue
        val year = date.year
        return "${year}년 ${month}월 ${weekOfYear}주차"
    }

    fun getDayForSearch(date: LocalDate): String {
        val year = date.year
        val month = date.monthValue
        val date = date.dayOfMonth
        return "${year}.${month}.${date}"
    }

    private fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate
    ): CalendarUiModel {
        return CalendarUiModel(
            selectedDate = toItemUiModel(lastSelectedDate, true),
            visibleDates = dateList.map {
                toItemUiModel(it, it.isEqual(lastSelectedDate))
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean) = CalendarUiModel.Date(
        isSelected = isSelectedDate,
        isToday = date.isEqual(today),
        date = date
    )
}