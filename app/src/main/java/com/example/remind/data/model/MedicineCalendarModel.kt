package com.example.remind.data.model

import java.time.LocalDate

data class MedicineCalendarModel(
    val totalDates: List<Date>
)

data class Date(
    val date: LocalDate
)
