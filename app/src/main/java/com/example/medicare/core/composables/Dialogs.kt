
package com.example.medicare.core.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.medicare.R

@Composable
fun ErrorDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = "Error") },
            text = { Text(text = "Something went wrong please try again later.") },
            confirmButton = {
                TextButton(onClick = onConfirmClick) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingDialog(
    showDialog: Boolean,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = { },
            title={
                Text(text = stringResource(id = R.string.sending_information))
            },
            text = {
                Row(modifier=Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    CircularProgressIndicator(
                        modifier = Modifier.weight(0.7f)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        )
    }
}