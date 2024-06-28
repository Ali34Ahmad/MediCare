package com.example.doctor.core.composables

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import com.example.doctor.R
import com.example.doctor.ui.theme.Spacing

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
            modifier = Modifier.size(120.dp),
        )
        Spacer(modifier = Modifier.height(Spacing.small))
        MedicareText(
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

    }
}

@Composable
fun MedicareText(
    style:TextStyle = MaterialTheme.typography.headlineLarge,
    modifier: Modifier = Modifier,
    color:Color=MaterialTheme.colorScheme.primary
) {
    Text(
        text = stringResource(id = R.string.app_name),
        style = style,
        //fontFamily = medicareFont,
        color = color,
        modifier=modifier
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun AppLogoPreview() {
    AppLogo(modifier = Modifier.width(120.dp))
}

