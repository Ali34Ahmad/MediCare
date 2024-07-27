package com.example.doctor.core.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.doctor.data.model.vaccine.Vaccine
import com.example.doctor.ui.theme.MediCareTheme
import com.example.doctor.ui.theme.Spacing

@Composable
fun DefaultVaccineTableList(
    modifier: Modifier = Modifier,
    defaultVaccineTable: List<Vaccine>,
    selectedVaccineIndex:Int?,
    onAvailableVaccineListItemClick:(Int)->Unit,
) {
    LazyRow(
        modifier=modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = Spacing.medium)
    ) {
        items(defaultVaccineTable.size){ index->
            AvailableVaccineListItem(
                title = defaultVaccineTable[index].name,
                onClick ={
                    onAvailableVaccineListItemClick(index)
                },
                isSelected = selectedVaccineIndex==index
            )
            Spacer(modifier =Modifier.width(Spacing.medium))
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableVaccineListItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    title: String,
    onClick: () -> Unit,
) {
    InputChip(
        modifier=modifier,
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall
            )
        },
        colors = InputChipDefaults.inputChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun AvailableVaccineListPreview() {
    MediCareTheme {
        Surface {
            DefaultVaccineTableList(defaultVaccineTable = listOf(
                Vaccine(name = "Vaccine 1"),
                Vaccine(name = "Vaccine 2"),
                Vaccine(name = "Vaccine 3"),
                Vaccine(name = "Vaccine 4"),
            ), selectedVaccineIndex = null) {

            }
        }
    }

}