package com.example.medicare.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dispensary.ui.composables.ChildCardComponent
import com.example.medicare.data.model.child.Child
import com.example.medicare.ui.theme.Spacing

@Composable
fun AddedChildrenList(
    modifier: Modifier=Modifier,
    children:List<Child> = emptyList(),
) {
    LazyColumn(modifier=modifier) {
        items(children){child->
            ChildCardComponent(
                child=child,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(Spacing.small))
        }
    }
}
