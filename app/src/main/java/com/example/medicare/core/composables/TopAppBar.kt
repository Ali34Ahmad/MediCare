package com.example.medicare.core.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
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
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicareTopAppBar(
    @StringRes title: Int,
    showNavigateUpIconButton: Boolean = false,
    onNavigateUpClick: () -> Unit = {},
    showNotificationIconButton: Boolean = true,
    onNotificationClick: ()->Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = title)) },
        navigationIcon = {
            if (showNavigateUpIconButton)
                IconButton(onClick = onNavigateUpClick) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(id = R.string.go_back)
                    )
                }
        },
        actions = {
            if (showNotificationIconButton)
                IconButton(onClick = onNotificationClick) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = stringResource(id = R.string.go_back)
                    )
                }
        },
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