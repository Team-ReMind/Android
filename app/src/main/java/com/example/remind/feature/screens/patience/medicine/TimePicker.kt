package com.example.remind.feature.screens.patience.medicine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat
import java.time.LocalTime

@Composable
fun TimePicker(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = {
            if (value < range.last) onValueChange(value + 1)
        }) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
        }
        Text(text = value.toString(), style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = {
            if (value > range.first) onValueChange(value - 1)
        }) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TimePickerExample() {
//    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }
//
//    CustomTimePicker(
//        initialTime = selectedTime ?: LocalTime.now(),
//        onTimeSelected = { time ->
//            selectedTime = time
//        }
//    )
//
//    selectedTime?.let {
//        Text("Selected Time: ${it.hour % 12}:${it.minute} ${if (it.hour < 12) "AM" else "PM"}")
//    }
//}
