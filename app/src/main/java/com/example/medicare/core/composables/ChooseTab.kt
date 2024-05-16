package com.example.dispensary.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicare.R
import com.example.medicare.ui.theme.primary_container

sealed class ChooseTabState {
    object First : ChooseTabState()
    object Second : ChooseTabState()
}

@Composable
fun ChooseTab(
    title: String?,
    modifier: Modifier = Modifier,
    isRequired: Boolean = true,
    @StringRes text1: Int,
    @StringRes text2: Int,
    errorMessage:String?="",
    chooseTabState: ChooseTabState?,
    onChooseChange: (ChooseTabState) -> Unit,
) {
    Column {
        Text(
            text = if (title == null) {
                ""
            } else {
                if (isRequired) "$title*" else title
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            bottomStart = 50.dp,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp,
                        ),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 50.dp,
                            bottomStart = 50.dp,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp,
                        )
                    )
                    .background(
                        color = when (chooseTabState) {
                            is ChooseTabState.First -> primary_container
                            is ChooseTabState.Second -> MaterialTheme.colorScheme.surface
                            else -> MaterialTheme.colorScheme.surface
                        }
                    )
                    .clickable { onChooseChange(ChooseTabState.First) },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = text1),
                    modifier = Modifier.padding(8.dp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            bottomStart = 0.dp,
                            topEnd = 50.dp,
                            bottomEnd = 50.dp,
                        ),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            bottomStart = 0.dp,
                            topEnd = 50.dp,
                            bottomEnd = 50.dp,
                        )
                    )
                    .background(
                        color = when (chooseTabState) {
                            is ChooseTabState.First -> MaterialTheme.colorScheme.surface
                            is ChooseTabState.Second -> primary_container
                            else -> MaterialTheme.colorScheme.surface
                        }
                    )
                    .clickable { onChooseChange(ChooseTabState.Second) },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = text2),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = errorMessage ?: "",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun ChooseTabPreview() {
    MaterialTheme {
        Surface {
            ChooseTab(
                text1 = R.string.male,
                text2 = R.string.female,
                chooseTabState = ChooseTabState.First,
                onChooseChange = {},
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.gender)
            )
        }
    }
}
