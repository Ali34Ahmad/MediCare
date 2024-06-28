package com.example.doctor.core.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.doctor.R
import com.example.doctor.ui.theme.tint

data class BottomNavigationItem(
    @StringRes val label: Int,
    val route: String,
)
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

@Composable
fun BottomNavBarComponent(
    modifier: Modifier = Modifier,
    onItem1Click: () -> Unit = {},
    onItem2Click: () -> Unit = {},
    selectedIndex: Int = 0,
    bottomNavItems: List<BottomNavigationItem>,
    listOfOutlinedIcons: List<Int>,
    listOfFilledIcons: List<Int>,
) {
    NavigationBar(modifier = modifier) {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = tint,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                ),
                selected = selectedIndex == index,
                onClick =
                when (index) {
                    0 -> onItem1Click
                    else -> onItem2Click
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selectedIndex == index) listOfFilledIcons[index]
                            else listOfOutlinedIcons[index]
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.label),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavBarComponentPreview() {
    MaterialTheme {
        Surface {
            BottomNavBarComponent(
                bottomNavItems = bottomNavItems,
                listOfFilledIcons = listOfFilledIcons,
                listOfOutlinedIcons = listOfOutlinedIcons
            )
        }
    }
}

