package com.example.medicare.presentation.notification

import androidx.lifecycle.ViewModel
import com.example.medicare.data.services.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationService: NotificationService,
):ViewModel() {
    private val _uiState= MutableStateFlow(NotificationUiState())
    val uiState=_uiState.asStateFlow()

    val notifications=notificationService.notifications

}