package com.example.dispensary.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicare.R

@Composable
fun ElevatedButtonComponent(
    @StringRes text:Int,
    onClick:()->Unit,
    modifier: Modifier=Modifier
) {
    Button(onClick = onClick,modifier=modifier) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyLarge
            )
    }
}

@Composable
fun OutlinedButtonComponent(
    @StringRes text:Int,
    onClick:()->Unit,
    modifier: Modifier=Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.outline
        )
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyMedium
            )
    }
}

@Composable
fun DatePickerButtonComponent(
    dayOfMonth: String,
    month:String,
    modifier:Modifier=Modifier,
) {
    Row(
        modifier = modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$month $dayOfMonth",
            modifier=Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_appointment_outlined),
            contentDescription = null,
            modifier=Modifier.size(18.dp)
        )
    }
}
@Preview(showBackground = true, widthDp = 360)
@Composable
private fun ElevatedButtonComponentPreview() {
    MaterialTheme{
        Surface {
            ElevatedButtonComponent(
                text = R.string.sign_up,
                onClick = {},
                modifier=Modifier.padding(16.dp)
            )
        }
    }
}
@Preview(showBackground = true, widthDp = 360)
@Composable
private fun OutlinedButtonComponentPreview() {
    MaterialTheme{
        Surface {
            OutlinedButtonComponent(
                text = R.string.sign_up,
                onClick = {},
                modifier=Modifier.padding(16.dp),

            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun DatePickerButtonComponentPreview() {
    MaterialTheme{
        Surface {
            DatePickerButtonComponent(
                month = stringResource(R.string.test_month),
                dayOfMonth = stringResource(id = R.string.test_day_of_month),
                modifier=Modifier.padding(16.dp),

            )
        }
    }
}