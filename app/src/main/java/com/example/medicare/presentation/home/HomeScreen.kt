package com.example.medicare.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun HomeScreen(
    userId: String,
    viewModel: HomeViewModel= hiltViewModel(),
) {
    val uiState=viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = Spacing.medium),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = userId)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    MediCareTheme {
        Surface {
            HomeScreen(userId = "Ali")
        }
    }
}