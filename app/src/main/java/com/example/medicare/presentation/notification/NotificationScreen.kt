package com.example.medicare.presentation.notification

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicare.core.composables.NotificationList
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun NotificationScreen(
    onNotificationCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    Spacer(modifier = Modifier.height(Spacing.medium))

    NotificationList(
        notifications = uiState.value.notificationList,
        modifier = modifier.padding(horizontal = Spacing.medium)
    )
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    MediCareTheme {
        Surface {
            NotificationScreen(onNotificationCardClick = { /*TODO*/ })
        }
    }
}