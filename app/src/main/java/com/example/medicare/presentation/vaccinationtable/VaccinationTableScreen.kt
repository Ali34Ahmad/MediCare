package com.example.medicare.presentation.vaccinationtable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicare.R
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.core.composables.VaccinationTableList
import com.example.medicare.core.composables.VaccinationTableListItem
import com.example.medicare.core.enums.AgeUnit
import com.example.medicare.core.enums.Month
import com.example.medicare.core.formatDate
import com.example.medicare.core.navigateUp
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.model.date.Age
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun VaccinationTableScreen(
    childId: String,
    onNavigateUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    uiState: VaccinationTableUiState,
    updateVaccinationTable: (String) -> Unit,
    vaccinationTable: List<VaccineTableItem>,
) {
    updateVaccinationTable(childId)

    Scaffold(
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
            VaccinationTableList(
                modifier = modifier,
                listOfVaccines = vaccinationTable
            )
        }
    }
}

@Preview
@Composable
private fun VaccinationTableScreenPreview() {
    MediCareTheme {
        Surface {
            VaccinationTableScreen(
                childId = "1",
                onNavigateUpClick = {},
                uiState = VaccinationTableUiState(
                    ""
                ),
                updateVaccinationTable = {},
                vaccinationTable = listOf(
                    VaccineTableItem(
                        id = "",
                        vaccine = Vaccine(
                            id = "",
                            name = "Vaccine name",
                            description = "This vaccine is shit",
                            fromAge = Age(10,AgeUnit.DAYS),
                            toAge = Age(2,AgeUnit.MONTHS),
                            availabilityStartDate = FullDate(10,Month.JUN,2023),
                            lastAvailableDate = FullDate(10,Month.JUN,2023),
                            conflicts = listOf("Conflict 1"),
                            visitNumber = 1
                        ),
                        vaccineDate = FullDate(10, Month.JUN, 2023)
                    ),
                    VaccineTableItem(
                        id = "",
                        vaccine = Vaccine(
                            id = "",
                            name = "Vaccine name",
                            description = "This vaccine is shit",
                            fromAge = Age(10,AgeUnit.DAYS),
                            toAge = Age(2,AgeUnit.MONTHS),
                            availabilityStartDate = FullDate(10,Month.JUN,2023),
                            lastAvailableDate = FullDate(10,Month.JUN,2023),
                            conflicts = listOf("Conflict 1"),
                            visitNumber = 1
                        ),
                        vaccineDate = FullDate(10, Month.JUN, 2023)
                    ),

                )
            )
        }
    }
}

