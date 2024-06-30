package com.example.doctor.core.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.doctor.ui.theme.MediCareTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    showBottomBar: Boolean,
    onScheduleItemClicked: () -> Unit,
    onProfileItemClicked: () -> Unit,
    selectedIndex: Int,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar)
                BottomNavBarComponent(
                    onItem1Click = onScheduleItemClicked,
                    onItem2Click = onProfileItemClicked,
                    selectedIndex = selectedIndex,
                    listOfOutlinedIcons = listOfOutlinedIcons,
                    listOfFilledIcons = listOfFilledIcons,
                    bottomNavItems = bottomNavItems
                )
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
                onScheduleItemClicked = {},
                onProfileItemClicked = {},
                selectedIndex = 0,
            ) {

            }
        }
    }
}