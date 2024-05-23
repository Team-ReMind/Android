package com.example.remind.feature.screens.patience.medicine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.response.MonthlyTakingMedicineDto
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun MedicineCalendar(
    modifier: Modifier = Modifier,
    monthData: List<MonthlyTakingMedicineDto>
) {
    val year = LocalDate.now().year
    val month = LocalDate.now().monthValue
    val dayOfWeek = remember { listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat") }
    val firstDayOfMonth = remember { LocalDate.of(year, month, 1) }
    val daysInMonth = remember { YearMonth.of(year, month).lengthOfMonth() }
    val firstDayOfWeek = remember { firstDayOfMonth.dayOfWeek.value % 7 }
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 9.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            dayOfWeek.forEach {day->
                Text(
                    text = day,
                    style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.text)
                )
            }
        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            var dayCounter = 1
            for (week in 0..5) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (day in 0..6) {
                        if (week == 0 && day < firstDayOfWeek || dayCounter > daysInMonth) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    .height(40.dp)
                            )
                        } else {
                            val date = monthData.find {it.date == dayCounter}
                            val background = when {
                                date?.takingLevel == 0 && date?.needMedicine == true -> RemindTheme.colors.sub_2
                                date?.takingLevel == 1 && date?.needMedicine == true -> RemindTheme.colors.main_3
                                date?.takingLevel == 2 && date?.needMedicine == true -> RemindTheme.colors.main_6
                                else -> RemindTheme.colors.white
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(13.dp)
                                    .height(30.dp)
                                    .background(shape = CircleShape, color = background),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = dayCounter.toString()
                                )
                                dayCounter++
                            }
                        }
                    }
                }
            }
        }
    }
}

