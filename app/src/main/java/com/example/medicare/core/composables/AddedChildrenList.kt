package com.example.medicare.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.dispensary.ui.composables.ChildCardComponent
import com.example.medicare.R
import com.example.medicare.ui.theme.Spacing

data class Child(
    val upNumber: String,
    val downNumber: String,
    val childName: String,
    val fatherName: String,
    val motherName: String,
    val dateOfBirth: String,
)
@Composable
fun AddedChildrenList(
    modifier: Modifier=Modifier,
    children:List<Child> = emptyList(),
) {
    val fakeList= listOf(
        Child(
            upNumber = stringResource(id = R.string.test_up_number),
            downNumber = stringResource(id = R.string.test_dow_number),
            childName = stringResource(id = R.string.test_child_name),
            fatherName = stringResource(id = R.string.test_father_name),
            motherName = stringResource(id = R.string.test_mother_name),
            dateOfBirth = stringResource(id = R.string.date_of_birth),
            ),
        Child(
            upNumber = stringResource(id = R.string.test_up_number),
            downNumber = stringResource(id = R.string.test_dow_number),
            childName = stringResource(id = R.string.test_child_name),
            fatherName = stringResource(id = R.string.test_father_name),
            motherName = stringResource(id = R.string.test_mother_name),
            dateOfBirth = stringResource(id = R.string.date_of_birth),
            ),
        Child(
            upNumber = stringResource(id = R.string.test_up_number),
            downNumber = stringResource(id = R.string.test_dow_number),
            childName = stringResource(id = R.string.test_child_name),
            fatherName = stringResource(id = R.string.test_father_name),
            motherName = stringResource(id = R.string.test_mother_name),
            dateOfBirth = stringResource(id = R.string.date_of_birth),
            )
    )
    LazyColumn(modifier=modifier) {
        items(fakeList){child->
            ChildCardComponent(
                upNumber = child.upNumber,
                downNumber = child.downNumber,
                childName = child.childName,
                fatherName = child.fatherName,
                motherName = child.motherName,
                dateOfBirth = child.dateOfBirth,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(Spacing.small))
        }
    }
}
