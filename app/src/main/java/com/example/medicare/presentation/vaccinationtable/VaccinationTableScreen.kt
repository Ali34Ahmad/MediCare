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
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
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
import com.example.medicare.core.enums.Month
import com.example.medicare.core.formatDate
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.model.date.Age
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun VaccinationTableScreen(
    modifier: Modifier = Modifier,
    viewModel: VaccinationTableViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    VaccinationTableList(
        modifier = modifier,
        listOfVaccines = uiState.value.listOfVaccines
    )
}

@Composable
fun VaccinationTableList(
    listOfVaccines: List<VaccineTableItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        item {
            VaccinationTableListFirstItem(modifier = Modifier.fillMaxWidth())
        }
        items(listOfVaccines) { vaccineTableItem ->
            VaccinationTableListItem(
                vaccineTableItem,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun VaccinationTableListItem(
    vaccineTableItem: VaccineTableItem,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .weight(0.3f)
                .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = vaccineTableItem.vaccine.visitNumber.toString(),
                modifier = Modifier
                    .padding(vertical = Spacing.small)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = vaccineTableItem.vaccine.name,
                modifier = Modifier
                    .padding(vertical = Spacing.small)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = vaccineTableItem.vaccineDate?.formatDate() ?: "...",
                modifier = Modifier
                    .padding(vertical = Spacing.small)
            )
        }
        Box(
            modifier = Modifier
                .weight(0.5f)
                .height(37.dp)
                .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector =
            if(vaccineTableItem.vaccineDate!=null) Icons.Outlined.Check
                else Icons.Outlined.AddCircle,
                contentDescription = null
            )
        }

    }
}

@Composable
fun VaccinationTableListFirstItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .weight(0.4f)
                .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.visit),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = Spacing.small)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.vaccine),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = Spacing.small)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.date),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = Spacing.small)
            )
        }
        Box(
            modifier = Modifier
                .weight(0.4f)
                .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.state),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = Spacing.small)
            )
        }
    }
}

@Preview
@Composable
private fun VaccinationTableListItemPreview() {
    MediCareTheme {
        Surface {
            VaccinationTableListItem(
                VaccineTableItem(
                    id = "",
                    vaccine = Vaccine(
                        name = "Vaccine name",
                        description = "",
                        fromAge = Age(),
                        toAge = Age(),
                        availabilityStartDate = FullDate(),
                        lastAvailableDate = FullDate(),
                        conflicts = emptyList(),
                        visitNumber = 1
                    ),
                    vaccineDate = FullDate(day = 1, month = Month.JUN, year = 2024),
                )
            )
        }
    }
}

@Preview
@Composable
private fun VaccinationTableListPreview() {
    MediCareTheme {
        Surface {
            VaccinationTableList(emptyList())
        }
    }
}

