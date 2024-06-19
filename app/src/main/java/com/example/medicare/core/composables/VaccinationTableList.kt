package com.example.medicare.core.composables

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.medicare.R
import com.example.medicare.core.formatDate
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.ui.theme.Spacing



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
        VaccinationTableCellItem(
            modifier=Modifier.weight(0.4f),
            text = vaccineTableItem.vaccine.visitNumber.toString()
        )
        VaccinationTableCellItem(
            modifier=Modifier.weight(1f),
            text = vaccineTableItem.vaccine.name
        )
        VaccinationTableCellItem(
            modifier=Modifier.weight(1f),
            text = vaccineTableItem.vaccineDate?.formatDate() ?: "..."
        )
        VaccinationTableStateCellItem(
            modifier=Modifier.weight(0.5f),
            vaccineDate = vaccineTableItem.vaccineDate
        )
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
        VaccinationTableCellItem(
            modifier=Modifier.weight(0.4f),
            text=stringResource(id = R.string.visit),
        )
        VaccinationTableCellItem(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.vaccine),
        )
        VaccinationTableCellItem(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.date),
        )
        VaccinationTableCellItem(
            modifier = Modifier.weight(0.5f),
            text = stringResource(id = R.string.state),
        )
    }
}


@Composable
fun VaccinationTableCellItem(
    modifier: Modifier=Modifier,
    text:String,
){
    Box(
        modifier = modifier
            .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(vertical = Spacing.small)
        )
    }
}
@Composable
fun VaccinationTableStateCellItem(
    modifier: Modifier=Modifier,
    vaccineDate:FullDate?,
){
    Box(
        modifier = modifier
            .height(37.dp)
            .border(width = 1.dp, shape = RectangleShape, color = Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector =
            if(vaccineDate!=null) Icons.Outlined.Check
            else Icons.Outlined.AddCircle,
            contentDescription = null
        )
    }
}
