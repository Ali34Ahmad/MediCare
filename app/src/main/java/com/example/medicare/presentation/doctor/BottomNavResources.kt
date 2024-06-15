package com.example.medicare.presentation.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dispensary.ui.composables.BottomNavBarComponent
import com.example.dispensary.ui.composables.BottomNavigationItem
import com.example.medicare.R
import com.example.medicare.ui.theme.primary_container
import com.example.medicare.ui.theme.tint

val listOfOutlinedIcons = listOf(
    R.drawable.ic_appointment_outlined,
    R.drawable.ic_profile_outlined,
)
val listOfFilledIcons = listOf(
    R.drawable.ic_appointment_filled,
    R.drawable.ic_profile_filled,
)


val bottomNavItems = listOf(
    BottomNavigationItem(
        label = R.string.schedule,
        route = "Home"
    ),
    BottomNavigationItem(
        label = R.string.profile,
        route = "Profile"
    ),
)
@Preview
@Composable
private fun DoctorBottomBarPreview() {
    BottomNavBarComponent(
        bottomNavItems = bottomNavItems,
        listOfOutlinedIcons = listOfOutlinedIcons,
        listOfFilledIcons = listOfFilledIcons
    )
}
