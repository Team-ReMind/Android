package com.example.remind.feature.screens.patience

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme
import com.jaikeerthick.composable_graphs.composables.line.LineGraph
import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphColors
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphFillType
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphVisibility
import com.jaikeerthick.composable_graphs.style.LabelPosition

@Composable
fun MedicineScreen() {
    RemindTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = RemindTheme.colors.grayscale_2
                )
                .padding(30.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 10.dp, bottom = 30.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ScoreEx()
            }
            Spacer(modifier = Modifier.width(25.dp))
            GraphComponent(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 10.dp, bottom = 30.dp, end = 40.dp)
            )
        }
    }
}


//그래프 연습용
@Composable
fun GraphComponent(
    modifier: Modifier = Modifier
){
    val xAxisData = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat") // xAxisData : List<GraphData>, and GraphData accepts both Number and String types
    val yAxisData = listOf(100, 75, 100, 25, 75, 50, 50)
    val dataModel = mutableListOf<LineData>()
    for(i in xAxisData.indices) {
        val lineData = LineData(xAxisData[i], yAxisData[i])
        dataModel.add(lineData)
    }
    Column() {
        val style = LineGraphStyle(
            visibility = LineGraphVisibility(
                isXAxisLabelVisible = true,
                isYAxisLabelVisible = false,
                isCrossHairVisible = false
            ),
            colors = LineGraphColors(
                lineColor = RemindTheme.colors.main_7,
                pointColor = RemindTheme.colors.black,
                clickHighlightColor = Color.Red,
                fillType = LineGraphFillType.Gradient(brush = Brush.verticalGradient(listOf(
                    RemindTheme.colors.main_7, RemindTheme.colors.white
                )))
            ),
            yAxisLabelPosition = LabelPosition.LEFT
        )
        LineGraph(
            data = dataModel,
            style = style,
            onPointClick = {}
        )
    }
    
}




@Composable
fun ScoreEx(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "0",
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        Spacer(modifier = modifier.height(3.dp))
        Image(
            modifier = modifier.size(15.dp, 20.dp),
            painter = painterResource(id = R.drawable.icadskfj),
            contentDescription = null
        )
        Text(
            text = "25",
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        Spacer(modifier = modifier.height(3.dp))
        Image(
            modifier = modifier.size(15.dp, 20.dp),
            painter = painterResource(id = R.drawable.icadskfj),
            contentDescription = null
        )
        Text(
            text = "50",
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        Spacer(modifier = modifier.height(3.dp))
        Image(
            modifier = modifier.size(15.dp, 20.dp),
            painter = painterResource(id = R.drawable.icadskfj),
            contentDescription = null
        )
        Text(
            text = "75",
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        Spacer(modifier = modifier.height(3.dp))
        Image(
            modifier = modifier.size(15.dp, 20.dp),
            painter = painterResource(id = R.drawable.icadskfj),
            contentDescription = null
        )
        Text(
            text = "100",
            style = TextStyle(
                fontSize = 10.sp
            )
        )
        Spacer(modifier = modifier.height(3.dp))
        Image(
            modifier = modifier.size(15.dp, 20.dp),
            painter = painterResource(id = R.drawable.icadskfj),
            contentDescription = null
        )
    }
}