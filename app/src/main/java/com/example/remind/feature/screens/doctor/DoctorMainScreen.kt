package com.example.remind.feature.screens.doctor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicListItem
import com.example.remind.core.common.component.RemindSearchTextField
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.feature.viewmodel.CustomViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoctorMain() {
    RemindTheme {
        val viewModel = CustomViewModel()
        val getAllData = viewModel.getAllData()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = RemindTheme.colors.white)
        ) {
            Appbar(modifier = Modifier)
            LazyColumn() {
                item {
                    Profile(modifier = Modifier)
                }
                stickyHeader {
                    StickyHeaderComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 7.dp)
                    )
                }
                itemsIndexed(getAllData) {index, item ->
                    var formattedIndex = ""
                    if(index<10) {
                        formattedIndex = String.format( "%02d", index + 1)
                    }
                    else {
                        formattedIndex = index.toString()
                    }
                    BasicListItem(
                        modifier = Modifier.padding(start = 26.dp, end = 28.dp),
                        name = item.name,
                        index = formattedIndex
                    )
                }
            }
        }
    }
}

@Composable
fun Appbar(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = modifier
                .size(width = 36.dp, height = 21.dp)
        )
        Spacer(Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_setting),
            contentDescription = null,
            modifier = modifier
                .size(width = 24.dp, height = 28.dp),
            tint = RemindTheme.colors.icon
        )
    }
}

@Composable
fun Profile(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //예비
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = modifier
                .size(width = 71.dp, height = 95.dp)
                .padding(start = 20.dp),
            tint = RemindTheme.colors.main_1
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = modifier.padding(end = 24.dp, bottom = 4.dp),
                text = "김말랑 님",
                style = RemindTheme.typography.b1Bold
            )
            Text(
                modifier = modifier.padding(end = 24.dp),
                text = stringResource(id = R.string.좋은_하루_되세요),
                style = RemindTheme.typography.h2Medium
            )
        }
    }
}

@Composable
fun StickyHeaderComponent(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = RemindTheme.colors.white)
    ) {
        Text(
            modifier = modifier.padding(
                start = 44.dp,
                top = 14.dp,
                bottom = 9.dp
            ),
            text = stringResource(R.string.관리_중인_환자),
            style = RemindTheme.typography.b3Bold.copy(color = RemindTheme.colors.main_6)
        )
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Box(
                modifier = modifier
                    .width(24.dp)
                    .height(1.dp)
                    .background(color = RemindTheme.colors.grayscale_2)
            )
            Box(
                modifier = modifier
                    .background(color = RemindTheme.colors.main_6)
                    .weight(1f)
                    .height(3.dp)
                    .clip(shape = RoundedCornerShape(5.dp))

            )
            Box(
                modifier = modifier
                    .width(24.dp)
                    .height(1.dp)
                    .background(color = RemindTheme.colors.grayscale_2)
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 35.dp, end = 28.dp, top = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                //api에서 받아와야함
                text = "총 60명",
            )
            RemindSearchTextField(
                modifier = Modifier.padding(start = 14.dp),
                hintText = stringResource(R.string.검색),
                onValueChange = {},
                text = ""
            )
            BasicButton(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(id = R.string.삭제),
                backgroundColor = RemindTheme.colors.main_4,
                textColor = RemindTheme.colors.white,
                verticalPadding = 18.dp,
                horizontalPadding = 5.dp,
                onClick = {}
            )
            BasicButton(
                modifier = Modifier.padding(start = 4.dp, end = 28.dp),
                text = stringResource(id = R.string.추가하기),
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 18.dp,
                horizontalPadding = 5.dp,
                onClick = {}
            )
        }
    }
}
