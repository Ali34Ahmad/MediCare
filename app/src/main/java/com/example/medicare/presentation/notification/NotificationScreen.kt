package com.example.medicare.presentation.notification

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicare.R
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.core.composables.NotificationList
import com.example.medicare.data.model.notification.Notification
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun NotificationScreen(
    onNavigateUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    uiState: NotificationUiState,
    listOfNotifications:List<Notification>
) {

    Scaffold(
        topBar = {
            MedicareTopAppBar(
                showNavigateUpIconButton = true,
                showNotificationIconButton = false,
                title = R.string.app_name,
                onNavigateUpClick =onNavigateUpClick
            )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column {
                Spacer(modifier = Modifier.height(Spacing.medium))

                NotificationList(
                    notifications = listOfNotifications,
                    modifier = modifier.padding(horizontal = Spacing.medium)
                )
            }
        }
    }
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    MediCareTheme {
        Surface {
            NotificationScreen(
                onNavigateUpClick = {  },
                uiState = NotificationUiState(),
                listOfNotifications = emptyList()
                )
        }
    }
}