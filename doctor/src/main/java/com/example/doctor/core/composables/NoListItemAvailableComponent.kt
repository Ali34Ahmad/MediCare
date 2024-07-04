package com.example.doctor.core.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.doctor.ui.theme.Spacing

@Composable
fun NoListItemAvailableComponent(
    modifier: Modifier =Modifier,
    @StringRes text:Int,
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(Spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Icon(imageVector = Icons.Outlined.WarningAmber, contentDescription = null)
        Spacer(modifier = Modifier.height(Spacing.small))
        Text(text = stringResource(id = text))
    }
}