package com.example.remind.feature.screens.patience_detail

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.remind.core.designsystem.theme.RemindTheme
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.marker.Marker
import com.patrykandpatrick.vico.views.chart.line.lineChart
import kotlin.random.Random

@Composable
fun PatienceDetailScreen() {
    RemindTheme {
        Column() {

        }
    }
}

@Composable
fun MoodGraph(context : Context) {
    val producer = ChartEntryModelProducer(getRandomEntries())
    Chart(
        chart = lineChart(
            context,

        ),
        chartModelProducer = producer,
        startAxis = startAxis(),
        bottomAxis = bottomAxis()
    )
}

fun getRandomEntries() = List(size=5) {
    25f * Random.nextFloat()
}.mapIndexed { x, y ->
    FloatEntry(
        x = x.toFloat(),
        y=y
    )
}

@Composable
@Preview
fun ScreenPreview() {
    MoodGraph(context = LocalContext.current)
}