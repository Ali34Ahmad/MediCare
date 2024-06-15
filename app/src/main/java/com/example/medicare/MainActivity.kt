package com.example.medicare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.medicare.fake.datalayer.DataViewModel
import com.example.medicare.fake.datalayer.Screen
import com.example.medicare.presentation.MedicareApp
import com.example.medicare.ui.theme.MediCareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel:DataViewModel = hiltViewModel<DataViewModel>()
            val clinics = viewModel.clinics.collectAsStateWithLifecycle()
            MediCareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen(
                        onClick1 = viewModel::addClinic,
                        list = clinics.value
                    )
                }
            }
        }
    }
}