package com.example.medicare.core.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.ChipDefaults
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun AvailableVaccineList(
    modifier: Modifier=Modifier,
    availableVaccines: List<Vaccine>,
    selectedVaccineIndex:Int?,
    onAvailableVaccineListItemClick:(Int)->Unit,
) {
    LazyRow(
        modifier=modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = Spacing.medium)
    ) {
        items(availableVaccines.size){ index->
            AvailableVaccineListItem(
                title = availableVaccines[index].name,
                onClick ={
                    onAvailableVaccineListItemClick(index)
                },
                isSelected = selectedVaccineIndex==index
            )
            Spacer(modifier = Modifier.width(Spacing.medium))
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
            AvailableVaccineList(availableVaccines = listOf(
                Vaccine(name = "Vaccine 1"),
                Vaccine(name = "Vaccine 2"),
                Vaccine(name = "Vaccine 3"),
                Vaccine(name = "Vaccine 4"),
            ), selectedVaccineIndex = null) {

            }
        }
    }

}