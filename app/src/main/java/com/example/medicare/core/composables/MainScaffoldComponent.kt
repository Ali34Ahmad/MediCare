package com.example.medicare.core.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dispensary.ui.composables.BottomNavBarComponent
import com.example.dispensary.ui.composables.bottomNavItems
import com.example.dispensary.ui.composables.listOfFilledIcons
import com.example.dispensary.ui.composables.listOfOutlinedIcons
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    showBottomBar: Boolean,
    showFloatingActionButton: Boolean,
    onHomeItemClicked: () -> Unit,
    onVaccinesItemClicked: () -> Unit,
    onAppointmentItemClicked: () -> Unit,
    onChildrenItemClicked: () -> Unit,
    onFloatingActionButtonClicked: () -> Unit,
    selectedIndex: Int,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar)
                BottomNavBarComponent(
                    onItem1Click = onHomeItemClicked,
                    onItem2Click = onVaccinesItemClicked,
                    onItem3Click = onAppointmentItemClicked,
                    onItem4Click = onChildrenItemClicked,
                    selectedIndex = selectedIndex,
                    listOfOutlinedIcons = listOfOutlinedIcons,
                    listOfFilledIcons = listOfFilledIcons,
                    bottomNavItems = bottomNavItems
                )
        },
        floatingActionButton = {
            if (showFloatingActionButton)
                FloatingActionButton(onClick = onFloatingActionButtonClicked) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = stringResource(id = R.string.add_child)
                    )
                }
        }
    ) { contentPadding ->
        Surface(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()) {
            content()
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MediCareTheme {
        Surface {
            MainScaffold(
                showBottomBar = true,
                showFloatingActionButton = false,
                onAppointmentItemClicked = {},
                onChildrenItemClicked = {},
                onHomeItemClicked = {},
                onVaccinesItemClicked = {},
                selectedIndex = 0,
                onFloatingActionButtonClicked = {}
            ) {

            }
        }
    }
}