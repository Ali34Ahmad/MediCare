package com.example.medicare.presentation.children

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.medicare.data.repositories.ChildRepository
import com.example.medicare.data.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChildrenViewModel @Inject constructor(
    private val childRepository: ChildRepository
):ViewModel() {
    private val _uiState= MutableStateFlow(ChildrenUiState())
    val uiState=_uiState.asStateFlow()

    val  children= childRepository.children
    init {
        try {
            updateLoadingDialogVisibilityState(true)
        }catch (e:Exception){
            updateLoadingDialogVisibilityState(false)
            updateErrorDialogVisibilityState()
            Log.e("Get children",e.message.toString())
        }
    }


    fun updateErrorDialogVisibilityState() {
        _uiState.value =
            _uiState.value.copy(showErrorDialog = !uiState.value.showErrorDialog)
    }
    fun updateLoadingDialogVisibilityState(newState:Boolean) {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = newState)
    }
    fun updateNoChildAddedYetState() {
        _uiState.value =
            _uiState.value.copy(showNoChildAdded = !uiState.value.showNoChildAdded)
    }

}