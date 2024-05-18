package com.example.medicare.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dispensary.ui.composables.SectionCardComponent
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

data class Section(
    val name:String,
    @DrawableRes val image:Int
)

val sectionList= listOf(
    Section(name="Section1",image= R.drawable.eye),
    Section(name="Section2",image= R.drawable.lungs),
    Section(name="Section3",image= R.drawable.brain),
    Section(name="Section4",image= R.drawable.heart),
    Section(name="Section5",image= R.drawable.urinary),
    Section(name="Section6",image= R.drawable.dental),
    Section(name="Section7",image= R.drawable.stomach),
)

@Composable
fun SectionsList(
    modifier:Modifier= Modifier,
    list: List<Section> = sectionList
) {
    LazyRow(
        modifier=modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = Spacing.medium)
        ) {
        items(list){section->
            SectionCardComponent(image = section.image, title = section.name)
            Spacer(modifier = Modifier.width(Spacing.medium))
        }
    }
}

@Preview
@Composable
private fun SectionsListPreview() {
    MediCareTheme {
        Surface {
            SectionsList()
        }
    }
}