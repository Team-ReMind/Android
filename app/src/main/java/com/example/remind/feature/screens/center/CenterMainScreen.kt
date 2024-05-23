package com.example.remind.feature.screens.center

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.remind.R
import com.example.remind.core.common.component.MainAppBar
import com.example.remind.core.designsystem.theme.RemindTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CenterMainScreen(
    modifier: Modifier = Modifier
) {
//    val context = LocalContext.current
//    //곧 삭제될 코드
//    val viewModel = CustomViewModel()
//    val getAllData = viewModel.getAllData()
//    val selectedIndex = remember {mutableStateOf(1)}
//    RemindTheme {
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .background(color = RemindTheme.colors.white)
//        ) {
//            MainAppBar(modifier = Modifier, onClick = {})
//            Spacer(modifier = Modifier.height(20.dp))
//            LazyColumn() {
//                item {
//                    Profile(
//                        modifier = modifier
//                            .padding(
//                                start = 20.dp,
//                                end = 20.dp
//                            ),
//                        context = context,
//                        imageUrl = "",
//                        name = "김말랑"
//                    )
//                }
//                stickyHeader {
//                    Column() {
//                        TabLayout(selectedIndex = selectedIndex)
//                        if(selectedIndex.value == 0) {
//                            StickyHeaderComponent(modifier = Modifier, onRegisterClicked = {}, 0)
//                        }
//                    }
//
//                }
//                itemsIndexed(getAllData) { index, item->
//                    val backgroundColor = if(index < 5) RemindTheme.colors.sub_1 else RemindTheme.colors.white
//                }
//            }
//        }
//    }
    val imageIndex = remember { mutableStateOf(0) }
    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = RemindTheme.colors.white)
        ) {
            MainAppBar(
                modifier = Modifier.padding(top = 23.6.dp),
                onClick = {
                    imageIndex.value = (imageIndex.value + 1) % 2
                }
            )
            if (imageIndex.value == 0) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ex_center_main),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            } else {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ex_center_sub),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    context: Context,
    imageUrl: String,
    name: String,
) {
    Row(
      modifier = modifier
    ) {
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions
                .bitmapTransform(RoundedCorners(80))
                .override(80,80)
            )
        Spacer(modifier.width(8.dp))
        Text(
            text = "${name}님,\n좋은 하루 되세요!",
            style = RemindTheme.typography.h2Medium
        )
    }
}

@Composable
@UiComposable
fun TabLayout(
    selectedIndex: MutableState<Int>
) {
    val tabTitle = listOf(stringResource(id = R.string.주의관리_필요_환자), stringResource(id = R.string.관리_중인_환자))
    val tabColors = listOf(RemindTheme.colors.sub_4, RemindTheme.colors.main_6)
    TabRow(
        selectedTabIndex = selectedIndex.value,
        containerColor = RemindTheme.colors.white,
        contentColor = RemindTheme.colors.grayscale_3,
        indicator = { tabPositions ->
            val color = tabColors[selectedIndex.value]
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex.value]),
                color = color
            )
        }
    ) {
        tabTitle.forEachIndexed { index, title ->
            Tab(
                selected = selectedIndex.value == index,
                onClick = { selectedIndex.value = index },
                text = {
                    Text(
                        text = title,
                        style = if(selectedIndex.value == index) RemindTheme.typography.b3Bold else RemindTheme.typography.b3Medium,
                        color = if(selectedIndex.value == index) tabColors[index] else RemindTheme.colors.grayscale_3
                    )
                }
            )
        }
    }
}

@Composable
fun DangerList(
    modifier: Modifier = Modifier,
    name: String,
    index: String,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(start = 11.dp, end = 9.dp, top = 10.dp, bottom = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

    }
}