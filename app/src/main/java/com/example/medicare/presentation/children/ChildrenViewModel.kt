package com.example.medicare.presentation.children

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChildrenViewModel @Inject constructor(

):ViewModel() {
    private val _uiState= MutableStateFlow(ChildrenUiState())
    val uiState=_uiState.asStateFlow()
}