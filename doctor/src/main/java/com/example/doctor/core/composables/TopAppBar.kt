package com.example.doctor.core.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.doctor.R
import com.example.doctor.ui.theme.MediCareTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicareTopAppBar(
    @StringRes title: Int,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = title)) },
        modifier = modifier.fillMaxWidth().shadow(elevation = 2.dp)
    )
}

@Preview
@Composable
private fun TopAppBarPreview() {
    MediCareTheme {
        Surface {
            MedicareTopAppBar(title = R.string.app_name)
        }
    }
}