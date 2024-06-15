package com.example.medicare.core.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dispensary.ui.composables.AppointmentReminderNotificationCardComponent
import com.example.dispensary.ui.composables.AvailableVaccinationNotificationCardComponent
import com.example.medicare.data.model.notification.Notification
import com.example.medicare.ui.theme.Spacing

@Composable
fun NotificationList(
    modifier: Modifier = Modifier,
    notifications: List<Notification> = emptyList(),
) {
    LazyColumn(modifier = modifier) {
        items(notifications) { notification ->
            when {
                 notification.doctorName.isBlank() ->
                    AppointmentReminderNotificationCardComponent(
                        notification=notification,
                        onClick = {}
                    )
                notification.vaccine==null->{
                    AvailableVaccinationNotificationCardComponent(
                        onClick = {},
                        notification=notification,
                    )
                }
            }
            Spacer(modifier = Modifier.height(Spacing.small))
        }
    }
}
