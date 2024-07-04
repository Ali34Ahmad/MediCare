package com.example.medicare.core.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onConfirmButtonClick: (LocalDate) -> Unit,
    datePickerState: UseCaseState
) {
    val currentDate = LocalDate.now()

    CalendarDialog(
        state = datePickerState,
        config = CalendarConfig(
            boundary =LocalDate.now()..currentDate.plusDays(30)
        ),
        selection = CalendarSelection.Date { date ->
            onConfirmButtonClick(date)
        }
    )
}

@Preview
@Composable
private fun DatePickerDialogPreview() {
    MyDatePickerDialog(
        onConfirmButtonClick = {},
        datePickerState = rememberUseCaseState()
    )
}