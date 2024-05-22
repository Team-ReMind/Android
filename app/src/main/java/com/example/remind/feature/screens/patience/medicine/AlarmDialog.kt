package com.example.remind.feature.screens.patience.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicDialog
import com.example.remind.core.designsystem.theme.RemindTheme
import java.text.DecimalFormat
import java.time.LocalTime

@Composable
fun AlarmDialog (
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    showDialog:Boolean,
) {
    BasicDialog(
        popupContent = {
            AlarmContent(
                onDismissClick = onDismissClick,
            )
        },
        showDialog = showDialog
    )
}

@Composable
fun AlarmContent(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = RemindTheme.colors.white,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column {
            Row{
                Text(
                    modifier = Modifier
                        .padding(top = 9.13.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.알림_추가),
                    style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.text)
                )

                Icon(
                    modifier = modifier
                        .padding(top = 8.83.dp, end = 11.dp)
                        .clickable(onClick = onDismissClick),
                    painter = painterResource(id = R.drawable.ic_close),
                    tint = RemindTheme.colors.icon,
                    contentDescription = null
                )
            }
            CustomTimePicker(
                onTimeSelected = {},
                onDismissClick = onDismissClick
            )
        }
    }
}

@Composable
fun CustomTimePicker(
    initialTime: LocalTime = LocalTime.now(),
    onTimeSelected: (LocalTime) -> Unit,
    onDismissClick: () -> Unit
) {
    var selectedHour by remember { mutableStateOf(if (initialTime.hour % 12 == 0) 12 else initialTime.hour % 12) }
    var selectedMinute by remember { mutableStateOf(initialTime.minute) }
    var isAM by remember { mutableStateOf(initialTime.hour < 12) }
    val df = DecimalFormat("00")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            androidx.compose.material3.Text(
                text = if (isAM) "오전" else "오후",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable(
                        onClick = { isAM = !isAM },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            TimePicker(
                value = df.format(selectedHour).toInt(),
                range = 1..12,
                onValueChange = { selectedHour = it }
            )
            androidx.compose.material3.Text(":", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
            TimePicker(
                value = selectedMinute,
                range = 0..59,
                onValueChange = { selectedMinute = it }
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        BasicButton(
            text = stringResource(id = R.string.완료),
            RoundedCorner = 12.dp,
            backgroundColor = RemindTheme.colors.main_6,
            textColor = RemindTheme.colors.white,
            verticalPadding = 13.dp,
            onClick = onDismissClick,
            textStyle = RemindTheme.typography.c3Bold
        )
    }
}

@Composable
fun SelectDay(
    modifier: Modifier = Modifier,
    checked: Boolean,
) {
    val dayList = listOf("일", "월", "화", "수", "목", "금", "토")
    var isChecked by remember{ mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.요일),
                style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.text)
            )
            Text(
                modifier = modifier.padding(start = 14.dp),
                text = stringResource(id = R.string.요일),
                style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.main_7)
            )
            Checkbox(
                modifier = modifier.padding(start = 4.dp),
                checked = isChecked,
                colors = CheckboxDefaults.colors(
                    checkedColor = RemindTheme.colors.main_6,
                    uncheckedColor = Color(0xFF6B7280),
                    checkmarkColor = RemindTheme.colors.white
                ),
                onCheckedChange = {isChecked = it}
            )
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for(i in 0..6) {

            }
        }
    }
}

@Composable
fun DayListComponent(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    borderColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier.padding(10.dp),
            text = text,
            style = RemindTheme.typography.h2Medium.copy(RemindTheme.colors.text)
        )
    }
}
