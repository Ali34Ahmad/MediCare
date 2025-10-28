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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.dispensary.ui.composables.ClinicInformationCardComponent
import com.example.dispensary.ui.composables.DatePickerButtonComponent
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.medicare.R
import com.example.medicare.core.composables.AvailableVaccineList
import com.example.medicare.core.composables.DaySocketHorizontalList
import com.example.medicare.core.composables.ErrorDialog
import com.example.medicare.core.composables.LoadingDialog
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.core.composables.MyDatePickerDialog
import com.example.medicare.core.composables.NoListItemAvailableComponent
import com.example.medicare.core.composables.TimeSocketsPager
import com.example.medicare.core.enums.TimeSocketState
import com.example.medicare.core.navigate
import com.example.medicare.core.toFullDate
import com.example.medicare.data.fake.vaccines
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.presentation.home.HomeScreenSection
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing
import java.time.LocalDate
import java.time.Month

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookAppointmentScreen(
    onBookNowButtonClick: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    onNavigateUpClick: () -> Unit,
    updateErrorDialogVisibilityState: (Boolean) -> Unit,
    clinicId: String,
    modifier: Modifier = Modifier,
    uiState: BookAppointmentUiState,
    userAndChildrenNames: List<String>,
    updateClinicEvent: (String) -> Unit,
    updateUserAndChildrenNamesEvent: (List<String>) -> Unit,
    updateBookedDateEvent: (LocalDate) -> Unit,
    slideToPreviousPageEvent: () -> Unit,
    slideToNextPageEvent: () -> Unit,
    updateChosenTimeSocketIndexEvent: (Int) -> Unit,
    updateChosenNameIndexEvent: (Int) -> Unit,
    updateNamesMenuVisibilityStateEvent: () -> Unit,
    availableVaccines: List<Vaccine>,
    onAvailableVaccineListItemClick: (index: Int, vaccineId: String) -> Unit,
) {
    try {
        updateClinicEvent(clinicId)
        updateUserAndChildrenNamesEvent(userAndChildrenNames)
    } catch (e: Exception) {
        e.printStackTrace()
    }


    val timeSockets =
        if (uiState.clinic.daySockets.isNotEmpty()) uiState.clinic.daySockets[0/*getDaySocketIndex(bookedDate)*/]
            .timeSockets.filter { timeSocket ->
                timeSocket.state == TimeSocketState.FREE
            }
        else emptyList()



    MyDatePickerDialog(
        onConfirmButtonClick = { date ->
            updateBookedDateEvent(date)
            uiState.datePickerState.hide()
        },
        datePickerState = uiState.datePickerState
    )

    ErrorDialog(
        showDialog = uiState.showErrorDialog,
        onDismissRequest = {
            updateErrorDialogVisibilityState(false)
        },
        onConfirmClick = {
            updateErrorDialogVisibilityState(false)
        }
    )
    LoadingDialog(
        showDialog = uiState.showLoadingDialog,
    )


    LaunchedEffect(uiState.isBookAppointmentIsSuccessful) {
        if (uiState.isBookAppointmentIsSuccessful)
            navigateToHomeScreen()
    }

    val snackBarHostState = remember { SnackbarHostState() }

    val errorMessage = stringResource(R.string.something_went_wrong)
    LaunchedEffect(uiState.showSnackBar) {
        if (uiState.showSnackBar) {
            snackBarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
            )
        },
        topBar = {
            MedicareTopAppBar(
                showNavigateUpIconButton = true,
                showNotificationIconButton = false,
                title = R.string.app_name,
                onNavigateUpClick = onNavigateUpClick
            )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(Spacing.medium))

                ClinicInformationCardComponent(
                    clinic = uiState.clinic,
                    modifier = Modifier.padding(horizontal = Spacing.medium)
                )

                Spacer(modifier = Modifier.height(Spacing.medium))

                HomeScreenSection(title = R.string.book_a_date) {
                    Column {
                        DatePickerButtonComponent(
                            dayOfMonth = uiState.bookedDate.dayOfMonth,
                            month = uiState.bookedDate.month.toString(),
                            onClick = {
                                uiState.datePickerState.show()
                            },
                            modifier = Modifier.padding(horizontal = Spacing.medium)
                        )
                        Spacer(modifier = Modifier.height(Spacing.medium))

                        DaySocketHorizontalList(
                            workDays = uiState.clinic.workDays,
                            selectedIndex = uiState.selectedDaySocketIndex,
                            updateSelectedIndex = updateBookedDateEvent
                        )
                    }

                }


                Spacer(modifier = Modifier.height(Spacing.medium))

                if (uiState.clinic.name == stringResource(id = R.string.vaccines)) {
                    if (availableVaccines.isNotEmpty()) {
                        HomeScreenSection(title = R.string.available_vaccines) {
                            Column {
                                AvailableVaccineList(
                                    availableVaccines = availableVaccines,
                                    selectedVaccineIndex = uiState.currentSelectedVaccineIndex,
                                    onAvailableVaccineListItemClick = { index ->
                                        onAvailableVaccineListItemClick(
                                            index,
                                            vaccines[index].id,
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.height(Spacing.medium))
                            }
                        }
                    } else {
                        NoListItemAvailableComponent(text = R.string.no_available_vaccines)
                    }
                }

                HomeScreenSection(title = R.string.available_times) {
                    TimeSocketsPager(
                        timeSockets = timeSockets,
                        onPreviousButtonClick = {
                            slideToPreviousPageEvent()
                        },
                        onNextButtonClick = {
                            slideToNextPageEvent()
                        },
                        pagerState = uiState.pagerState,
                        modifier = Modifier.padding(horizontal = Spacing.small),
                        onClick = {
                            updateChosenTimeSocketIndexEvent(it)
                        },
                        currentSelectedTimeSocketIndex = uiState.chosenTimeSocketIndex
                    )
                }

                Spacer(modifier = Modifier.height(Spacing.large))

                ChoosePatientNameSection(
                    chosenNameIndex = uiState.chosenNameIndex,
                    showMenu = uiState.isNamesMenuVisible,
                    listOfNames = uiState.userAndChildrenNames,
                    modifier = Modifier.padding(horizontal = Spacing.medium),
                    onMenuItemClick = { index ->
                        updateChosenNameIndexEvent(index)
                    },
                    onClick = {
                        updateNamesMenuVisibilityStateEvent()
                    },
                    onDismissRequest = {
                        updateNamesMenuVisibilityStateEvent()
                    }
                )

                Spacer(modifier = Modifier.height(Spacing.large))

                ElevatedButtonComponent(
                    text = R.string.book_now,
                    onClick = {
                        onBookNowButtonClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spacing.medium)
                )
                Spacer(modifier = Modifier.height(Spacing.large))
            }
        }
    }
}


@Composable
fun PagerController(
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    currentPagerIndex: Int = 0,
    pagesNumber: Int = 0,
    modifier: Modifier = Modifier,
) {
    if (pagesNumber != 0) {
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

            Text(text = "${currentPagerIndex + 1}/${pagesNumber}")

            Spacer(modifier = Modifier.width(Spacing.small))

            IconButton(onClick = onNextButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.NavigateNext,
                    contentDescription = stringResource(id = R.string.next)
                )
            }
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
            onDismissRequest = onDismissRequest
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
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .width(116.dp)
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
                text = if (listOfNames.isNotEmpty())
                    listOfNames[chosenNameIndex]
                else stringResource(
                    id = R.string.none
                ),
                modifier = Modifier
                    .padding(vertical = Spacing.small, horizontal = Spacing.small)
                    .width(80.dp),
                overflow = TextOverflow.Ellipsis,
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
            properties = PopupProperties(focusable = true),
            modifier = Modifier.width(116.dp)
        ) {
            listOfNames.forEachIndexed { index, name ->
                DropdownMenuItem(
                    text = {
                        Text(
                            name,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    onClick = {
                        onMenuItemClick(index)
                        onDismissRequest()
                    },
                    modifier = Modifier
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
            BookAppointmentScreen(
                onBookNowButtonClick = {},
                clinicId = "",
                onNavigateUpClick = {},
                updateBookedDateEvent = {},
                updateChosenNameIndexEvent = {},
                updateChosenTimeSocketIndexEvent = {},
                updateClinicEvent = {},
                updateNamesMenuVisibilityStateEvent = {},
                updateUserAndChildrenNamesEvent = {},
                userAndChildrenNames = listOf(),
                navigateToHomeScreen = {},
                slideToNextPageEvent = {},
                slideToPreviousPageEvent = {},
                uiState = BookAppointmentUiState(),
                availableVaccines = emptyList(),
                onAvailableVaccineListItemClick = { int, string -> },
                updateErrorDialogVisibilityState = {}
            )
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
                onDismissRequest = {}
            )
        }
    }
}