package com.example.medicare.core.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dispensary.ui.composables.DateCardComponent
import com.example.medicare.data.fake.listOfWorkDays
import com.example.medicare.core.getDayOfWeek
import com.example.medicare.core.getMonthByJavaMonth
import com.example.medicare.core.isNotWorkDay
import com.example.medicare.core.toLocalDate
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.date.WorkDay
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing
import java.time.LocalDate

@Composable
fun DaySocketHorizontalList(
    modifier:Modifier=Modifier,
    workDays:List<WorkDay>,
    selectedIndex:Int,
    updateSelectedIndex:(LocalDate)->Unit,
) {
    val dates=generateDaySocketsList()
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Spacing.medium)
    ) {
        items(dates.size) { currentIndex ->
            DateCardComponent(
                onClick = {
                    updateSelectedIndex(dates[currentIndex].toLocalDate())
                },
                date = dates[currentIndex],
                isSelected = selectedIndex==currentIndex,
                isDisabled=dates[currentIndex].getDayOfWeek().isNotWorkDay(workDays = workDays)
            )
            Spacer(modifier = Modifier.width(Spacing.small))
        }
    }
}

fun generateDaySocketsList():List<FullDate>{
    val daySockets:MutableList<FullDate> = mutableListOf()

    val currentDate = LocalDate.now()

    for (i in 0..30) {
        val nextDate = currentDate.plusDays(i.toLong())
        daySockets.add(FullDate(nextDate.dayOfMonth, nextDate.month.getMonthByJavaMonth(), nextDate.year))
    }

    return daySockets
}

@Preview
@Composable
private fun DaySocketHorizontalListPreview() {
    MediCareTheme {
        Surface {
            DaySocketHorizontalList(
                modifier = Modifier.fillMaxWidth(),
                selectedIndex = 0,
                updateSelectedIndex = {},
                workDays = listOfWorkDays
            )
        }
    }
}