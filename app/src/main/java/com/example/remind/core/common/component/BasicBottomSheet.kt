package com.example.remind.core.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.remind.core.designsystem.theme.RemindTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicBottomSheet(
    modifier: Modifier = Modifier,
    sheetState : SheetState = rememberModalBottomSheetState(),
    onDismissRequest: () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = RemindTheme.colors.white,
        scrimColor = RemindTheme.colors.black.copy(alpha = 0.4f)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        ) {
            sheetContent()
        }
    }
}