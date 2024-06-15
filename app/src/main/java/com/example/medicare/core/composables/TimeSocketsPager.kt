package com.example.medicare.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dispensary.ui.composables.TimeSocketCardComponent
import com.example.medicare.core.enums.DayPeriod
import com.example.medicare.core.enums.TimeSocketState
import com.example.medicare.data.model.date.Time
import com.example.medicare.data.model.date.TimeSocket
import com.example.medicare.presentation.bookappointment.PagerController
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun TimeSocketsPager(
    timeSockets: List<TimeSocket>,
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick:(Int)->Unit,
    currentSelectedTimeSocketIndex:Int?=null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
        ) { page ->
            TimeSocketGrid(
                timeSockets.subList(page * 6, page * 6 + 6),
                onClick = onClick,
                currentSelectedTimeSocketIndex=currentSelectedTimeSocketIndex
            )
        }
        PagerController(
            onPreviousButtonClick = onPreviousButtonClick,
            onNextButtonClick = onNextButtonClick,
            currentPagerIndex = pagerState.currentPage
        )
    }

}

@Composable
fun TimeSocketGrid(
    timeSockets: List<TimeSocket>,
    currentSelectedTimeSocketIndex: Int? = null,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            vertical = Spacing.small
        ),
        userScrollEnabled = false,
    ) {
        items(timeSockets.size) { index ->
            TimeSocketCardComponent(
                timeSocket = timeSockets[index],
                color =
                if (currentSelectedTimeSocketIndex != null && currentSelectedTimeSocketIndex == index)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.background,
                onClick = {
                    onClick(it)
                },
                currentIndex = index,
                modifier = Modifier.padding(Spacing.small)
            )
        }
    }
}

@Preview
@Composable
private fun TimeSocketGridPreview() {
    MediCareTheme {
        Surface {
            TimeSocketGrid(
                listOf(
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE

                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE

                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE

                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE

                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE

                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE

                    ),
                ),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun TimeSocketPagerPreview() {
    MediCareTheme {
        Surface {
            TimeSocketsPager(
                listOf(
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(9, 0, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),

                    TimeSocket(
                        Time(1, 1, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(1, 1, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(1, 1, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(1, 1, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(1, 1, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                    TimeSocket(
                        Time(1, 1, DayPeriod.AM),
                        state = TimeSocketState.FREE
                    ),
                ),
                onNextButtonClick = {},
                onPreviousButtonClick = {},
                pagerState = rememberPagerState(0, pageCount = { 4 }),
                onClick = {}
            )
        }
    }
}

