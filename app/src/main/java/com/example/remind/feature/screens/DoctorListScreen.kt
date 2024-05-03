package com.example.remind.feature.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.remind.core.common.component.BasicListItem
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.feature.viewmodel.CustomViewModel

@Composable
fun DoctorListScreen() {
    RemindTheme {
        val viewModel = CustomViewModel()
        val getAllData = viewModel.getAllData()
        LazyColumn() {
            itemsIndexed(getAllData) {index, item ->
                var formattedIndex = ""
                if(index<10) {
                    formattedIndex = String.format( "%02d", index + 1)
                }
                else {
                    formattedIndex = index.toString()
                }
                BasicListItem(name = item.name, index = formattedIndex)
            }
        }
    }
}

//@Composable
//fun PatientList() {
//    val people = remember {Example.ItemList}
//    LazyColumn(
//        contentPadding = PaddingValues(horizontal = 26.dp)
//    ) {
//        items(
//            items = people,
//            itemContent = {
//
//            }
//        )
//    }
//}




