package com.example.medicare.core.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicare.R
import com.example.medicare.ui.theme.Spacing

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.medicare_logo),
            contentDescription = null,
            modifier=Modifier.size(120.dp),
        )
        Spacer(modifier = Modifier.height(Spacing.small))
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            //fontFamily = medicareFont,
            color= MaterialTheme.colorScheme.primary
        )

    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun AppLogoPreview() {
    AppLogo(modifier = Modifier.width(120.dp))
}

