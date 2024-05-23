package com.example.remind.feature.screens.doctor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicListItem
import com.example.remind.core.common.component.MainAppBar
import com.example.remind.core.common.component.RemindSearchTextField
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.response.Info
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoctorMain(
    navController: NavHostController,
    viewModel: DoctorViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getPatients()
        viewModel.getRequestPatients()
        viewModel.getMemberInfo()
    }

    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is DoctorContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
            }
        }
    }

    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = RemindTheme.colors.white)
        ) {
            MainAppBar(
                modifier = Modifier.padding(top = 23.6.dp),
                onClick = {}
            )
            LazyColumn(
                modifier = Modifier.padding(start = 26.dp, end = 28.dp, top = 20.dp),
            ) {
                item {
                    Profile(
                        modifier = Modifier,
                        myInfo = uiState.memberInfo
                    )
                }
                stickyHeader {
                    StickyHeaderComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 7.dp),
                        onRegisterClicked = {
                            viewModel.setEvent(DoctorContract.Event.RegisterButtonClicked(context))
                        },
                        num = uiState.doctorData.patientNumber
                    )
                }
                itemsIndexed(uiState.doctorData.patientDtos) {index, item ->
                    val backgroundColor = if(index % 2 == 0) RemindTheme.colors.main_1 else RemindTheme.colors.white
                    var formattedIndex = ""
                    if(index<10) {
                        formattedIndex = String.format( "%02d", index + 1)
                    }
                    else {
                        formattedIndex = index.toString()
                    }
                    BasicListItem(
                        modifier = Modifier,
                        name = item.name,
                        index = formattedIndex,
                        backgroundColor = backgroundColor,
                        catiousClicked = {
                            viewModel.setEvent(
                                DoctorContract.Event.ClickedToManage(item.memberId)
                            )
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Profile(
    modifier: Modifier,
    myInfo: Info
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .size(width = 80.dp, height = 80.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop,
            model = myInfo.imageUrl,
            contentDescription = null
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = modifier.padding(end = 24.dp, bottom = 4.dp),
                text = "${myInfo.name}님",
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
fun StickyHeaderComponent(
    modifier: Modifier,
    onRegisterClicked: () -> Unit,
    num: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()

    ) {
        Text(
            modifier = modifier
                .background(color = RemindTheme.colors.white)
                .padding(
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
                modifier = Modifier
                    .background(color = RemindTheme.colors.grayscale_2)
                    .width(24.dp)
                    .height(1.dp)
            )
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(color = RemindTheme.colors.main_6)
                    .weight(1f)
                    .height(3.dp)


            )
            Box(
                modifier = Modifier
                    .background(color = RemindTheme.colors.grayscale_2)
                    .width(24.dp)
                    .height(1.dp)
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 35.dp, end = 28.dp, top = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "총 ${num}명",
                style = RemindTheme.typography.c1Medium
            )
            RemindSearchTextField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 14.dp),
                hintText = stringResource(R.string.검색),
                onValueChange = {},
                text = ""
            )
            BasicButton(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(1f),
                text = stringResource(id = R.string.삭제),
                RoundedCorner= 20.dp,
                backgroundColor = RemindTheme.colors.main_4,
                textColor = RemindTheme.colors.white,
                verticalPadding = 3.dp,
                onClick = {},
                textStyle = RemindTheme.typography.c1Medium
            )
            BasicButton(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(1f),
                text = stringResource(id = R.string.추가하기),
                RoundedCorner= 20.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding =5.dp,
                onClick = onRegisterClicked,
                textStyle = RemindTheme.typography.c1Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorMainPreview() {

}


