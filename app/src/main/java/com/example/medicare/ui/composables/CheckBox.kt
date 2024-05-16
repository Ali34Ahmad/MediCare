package com.example.dispensary.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme

@Composable
fun CheckBoxComponent(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    text1: String, style1: TextStyle = TextStyle.Normal,
    text2: String = "", style2: TextStyle = TextStyle.Bold,
    text3: String = "", style3: TextStyle = TextStyle.Normal,
    text4: String = "", style4: TextStyle = TextStyle.Bold,
    text5: String = "", style5: TextStyle = TextStyle.Normal,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onCheckedChange(checked)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
        //Spacer(modifier = Modifier.width(4.dp))
        TextWithMultipleStyles(
            text1 = text1,
            style1 = style1,
            text2 = text2,
            style2 = style2,
            text3 = text3,
            style3 = style3,
            text4 = text4,
            style4 = style4,
            text5 = text5,
            style5 = style5,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCheckedChange(!checked) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxComponentPreview() {
    MediCareTheme {
        Surface {
            CheckBoxComponent(
                checked = false,
                onCheckedChange = {},
                text1 = stringResource(id = R.string.checkbox_auth_text1),
                text2 = stringResource(id = R.string.checkbox_auth_text2),
                text3 = stringResource(id = R.string.checkbox_auth_text3),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}