package com.example.medicare.presentation.children

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.repositories.ChildRepository
import com.example.medicare.data.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildrenViewModel @Inject constructor(
    private val childRepository: ChildRepository
):ViewModel() {
    private val _uiState= MutableStateFlow(ChildrenUiState())
    val uiState=_uiState.asStateFlow()

    lateinit var children: Flow<List<Child>>

    init {
        try {
            updateErrorDialogVisibilityState(false)
            updateLoadingDialogVisibilityState(true)
            children= childRepository.children
            updateLoadingDialogVisibilityState(false)
        }catch (e:Exception){
            updateLoadingDialogVisibilityState(false)
            updateErrorDialogVisibilityState(true)
            Log.e("Children",e.message?:"Error")
        }
    }

    fun updateErrorDialogVisibilityState(isVisible:Boolean) {
        _uiState.value =
            _uiState.value.copy(showErrorDialog = isVisible)
    }
    fun updateLoadingDialogVisibilityState(newState:Boolean) {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = newState)
    }
    fun updateNoChildAddedYetState(showNoChildAdded:Boolean) {
        _uiState.value =
            _uiState.value.copy(showNoChildAdded = showNoChildAdded)
    }
}