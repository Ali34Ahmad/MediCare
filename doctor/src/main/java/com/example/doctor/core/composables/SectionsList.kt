package com.example.doctor.core.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.ui.theme.MediCareTheme
import com.example.doctor.ui.theme.Spacing

data class Section(
    val name:String,
    @DrawableRes val image:Int
)

@Composable
fun SectionsList(
    modifier:Modifier= Modifier,
    clinics: List<Clinic>,
    onClick:(Int)->Unit,
    currentIndex:Int
) {
    LazyRow(
        modifier=modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = Spacing.medium)
        ) {
        items(clinics.size){ index->
            SectionCardComponent(
                imageUrl = "clinic.imageUrl",
                title = clinics[index].name,
                onClick ={
                    onClick(index)
                },
                isSelected = currentIndex==index
            )
            Spacer(modifier = Modifier.width(Spacing.medium))
        }
    }
}

@Preview
@Composable
private fun SectionsListPreview() {
    MediCareTheme {
        Surface {
            SectionsList(onClick = {}, clinics = emptyList(), currentIndex = 0)
        }
    }
}