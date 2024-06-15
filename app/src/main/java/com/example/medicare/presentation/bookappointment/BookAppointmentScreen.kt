package com.example.medicare.presentation.bookappointment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material.icons.outlined.NavigateBefore
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dispensary.ui.composables.ClinicInformationCardComponent
import com.example.dispensary.ui.composables.DatePickerButtonComponent
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.medicare.R
import com.example.medicare.core.composables.TimeSocketsPager
import com.example.medicare.core.enums.TimeSocketState
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.presentation.home.HomeScreenSection
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookAppointmentScreen(
    onBookNowButtonClick: () -> Unit,
    clinic: Clinic,
    modifier: Modifier = Modifier,
    viewModel: BookAppointmentViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val currentUserName=viewModel.userName?:"Username"
    val children=viewModel.listOfChildren.collectAsStateWithLifecycle(initialValue = emptyList()).value

    viewModel.updateClinic(clinic)

    val childrenNames:MutableList<String> = mutableListOf(currentUserName)
    children.forEach { child->
        childrenNames.add(child.firstName)
    }

    val bookedDate=FullDate(
        year=uiState.value.bookedDate.year,
        month = viewModel.getMonthByJavaMonth(uiState.value.bookedDate.month),
        day=uiState.value.bookedDate.dayOfMonth.toString()
    )
    val timeSockets=uiState.value.clinic.daySockets[viewModel.getDaySocketIndex(bookedDate)]
        .timeSockets.filter {
                timeSocket -> timeSocket.state==TimeSocketState.FREE
        }

    MyDatePickerDialog(
        onConfirmButtonClick = { date ->
            viewModel.updateBookedDate(date)
            uiState.value.datePickerState.hide()
        },
        datePickerState = uiState.value.datePickerState
    )


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(Spacing.medium))

        ClinicInformationCardComponent(
            clinic=uiState.value.clinic,
            modifier = Modifier.padding(horizontal = Spacing.medium)
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        HomeScreenSection(title = R.string.book_a_date) {
            Column {
                DatePickerButtonComponent(
                    dayOfMonth = uiState.value.bookedDate.dayOfMonth,
                    month = uiState.value.bookedDate.month.toString(),
                    onClick = {
                        uiState.value.datePickerState.show()
                    },
                    modifier = Modifier.padding(horizontal = Spacing.medium)
                )
            }
        }


        HomeScreenSection(title = R.string.available_times) {
            TimeSocketsPager(
                timeSockets = timeSockets,
                onPreviousButtonClick = {
                    viewModel.slideToPreviousPage()
                },
                onNextButtonClick = {
                    viewModel.slideToNextPage()
                },
                pagerState = uiState.value.pagerState,
                modifier = Modifier.padding(horizontal = Spacing.small),
                onClick = {
                    viewModel.updateChosenTimeSocketIndex(it)
                },
                currentSelectedTimeSocketIndex=uiState.value.chosenTimeSocketIndex
            )
        }

        Spacer(modifier = Modifier.height(Spacing.large))

        ChoosePatientNameSection(
            chosenNameIndex = uiState.value.chosenNameIndex,
            showMenu = uiState.value.isNamesMenuVisible,
            listOfNames = childrenNames,
            modifier = Modifier.padding(horizontal = Spacing.medium),
            onMenuItemClick = { index ->
                viewModel.updateChosenNameIndex(index)
            },
            onClick = {
                viewModel.updateNamesMenuVisibilityState()
            },
            onDismissRequest={
                viewModel.updateNamesMenuVisibilityState()
            }
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        ElevatedButtonComponent(
            text = R.string.book_now,
            onClick = {
                viewModel.bookAppointment(onBookNowButtonClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.medium)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onConfirmButtonClick: (LocalDate) -> Unit,
    datePickerState: UseCaseState
) {

    CalendarDialog(
        state = datePickerState,
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

@Composable
fun PagerController(
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    currentPagerIndex: Int = 0,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousButtonClick) {
            Icon(
                imageVector = Icons.Outlined.NavigateBefore,
                contentDescription = stringResource(id = R.string.previous)
            )
        }
        Spacer(modifier = Modifier.width(Spacing.small))

        Text(text = "${currentPagerIndex + 1}/4")

        Spacer(modifier = Modifier.width(Spacing.small))

        IconButton(onClick = onNextButtonClick) {
            Icon(
                imageVector = Icons.Outlined.NavigateNext,
                contentDescription = stringResource(id = R.string.next)
            )
        }
    }
}

@Composable
fun ChoosePatientNameSection(
    chosenNameIndex: Int,
    showMenu: Boolean = false,
    listOfNames: List<String>,
    onMenuItemClick: (Int) -> Unit,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.appointment_for),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(1f))
        ChoosePatientNameDropDownMenu(
            chosenNameIndex = chosenNameIndex,
            showMenu = showMenu,
            listOfNames = listOfNames,
            modifier = Modifier.weight(1f),
            onMenuItemClick = onMenuItemClick,
            onClick = onClick,
            onDismissRequest=onDismissRequest
        )
    }
}

@Composable
fun ChoosePatientNameDropDownMenu(
    chosenNameIndex: Int,
    showMenu: Boolean = false,
    listOfNames: List<String>,
    onMenuItemClick: (Int) -> Unit,
    onClick: () -> Unit,
    onDismissRequest:()->Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .width(110.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = MaterialTheme.shapes.small
                )
                .clickable {
                    onClick()
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (listOfNames.isNotEmpty()) listOfNames[chosenNameIndex] else stringResource(
                    id = R.string.none
                ),
                modifier = Modifier
                    .padding(vertical = Spacing.small, horizontal = Spacing.small)
            )
            Icon(
                imageVector = if (showMenu) Icons.Outlined.ArrowDropUp else Icons.Outlined.ArrowDropDown,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(Spacing.small))
        }
        Spacer(modifier = Modifier.height(Spacing.extraSmall))

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = onDismissRequest,
            properties = PopupProperties(focusable = true)
        ) {
            listOfNames.forEachIndexed{index,name->
                DropdownMenuItem(
                    text = { Text(name) },
                    onClick = {
                        onMenuItemClick(index)
                        onDismissRequest()
                    },
                    modifier=Modifier
                    .background(
                        color = if (chosenNameIndex == index)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.background
                        )
                )
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun BookAppointmentScreenPreview() {
    MediCareTheme {
        Surface {
            BookAppointmentScreen(onBookNowButtonClick = {}, clinic = Clinic())
        }
    }
}

@Preview
@Composable
private fun ChoosePatientNameDropDownMenuPreview() {
    MediCareTheme {
        Surface {
            ChoosePatientNameDropDownMenu(
                chosenNameIndex = 0,
                listOfNames = listOf("Name 1", "Name 2", "Name 3", "Name 4"),
                onMenuItemClick = {},
                showMenu = true,
                onClick = {},
                onDismissRequest={}
            )
        }
    }
}