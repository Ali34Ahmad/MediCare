package com.example.dispensary.ui.composables

import android.text.SpannedString
import android.text.style.ClickableSpan
import androidx.compose.foundation.CombinedClickableNode
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.medicare.R


sealed class TextStyle {
    object Normal : TextStyle()
    object Bold : TextStyle()
}

@Composable
fun TextWithMultipleStyles(
    modifier: Modifier = Modifier,
    fontSizeSp:Int=14,
    text1: String, style1: TextStyle = TextStyle.Normal,color1:Color=MaterialTheme.colorScheme.onSurface,
    text2: String = "", style2: TextStyle = TextStyle.Bold,color2:Color=MaterialTheme.colorScheme.onSurface,
    text3: String = "", style3: TextStyle = TextStyle.Normal,color3:Color=MaterialTheme.colorScheme.onSurface,
    text4: String = "", style4: TextStyle = TextStyle.Bold,color4:Color=MaterialTheme.colorScheme.onSurface,
    text5: String = "", style5: TextStyle = TextStyle.Normal,color5:Color=MaterialTheme.colorScheme.onSurface,
) {
    val text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontWeight = when (style1) {
                    is TextStyle.Normal -> FontWeight.Normal
                    is TextStyle.Bold -> FontWeight.Bold
                },
                color = color1,
            )
        ) {
            append("$text1 ")
        }
        withStyle(
            SpanStyle(
                fontWeight = when (style2) {
                    is TextStyle.Normal -> FontWeight.Normal
                    is TextStyle.Bold -> FontWeight.Bold
                },
                color = color2,
            )
        ) {
            append("$text2 ")
        }
        withStyle(
            SpanStyle(
                fontWeight = when (style3) {
                    is TextStyle.Normal -> FontWeight.Normal
                    is TextStyle.Bold -> FontWeight.Bold
                },
                color = color3,
            )
        ) {
            append("$text3 ")
        }
        withStyle(
            SpanStyle(
                fontWeight = when (style4) {
                    is TextStyle.Normal -> FontWeight.Normal
                    is TextStyle.Bold -> FontWeight.Bold
                },
                color = color4,
            )
        ) {
            append("$text4 ")
        }
        withStyle(
            SpanStyle(
                fontWeight = when (style5) {
                    is TextStyle.Normal -> FontWeight.Normal
                    is TextStyle.Bold -> FontWeight.Bold
                },
                color = color5,
            )
        ) {
            append(text5)
        }


    }
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontSize = fontSizeSp.sp,
        modifier = modifier
    )
}

@Composable
fun SpannableTextComponent(
    text1: String,
    text2: String,
    onCLick: () -> Unit,
    modifier: Modifier=Modifier
) {

    val text = "$text1 $text2"
    val start=text.indexOf(text2)
    val end=start+text1.length
    val annotatedString = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontWeight =FontWeight.Normal
            )
        ) {
            append("$text1 ")
        }
        pushStringAnnotation(tag = "clickable", annotation = text2)
        withStyle(
            SpanStyle(
                fontWeight =FontWeight.Bold,
            )
        ) {
            append("$text2 ")
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        modifier=modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "clickable", start = start, end = end)
                .firstOrNull()?.let { annotation ->
                onCLick()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SpannableTextComponentPreview() {
    SpannableTextComponent(text1 = "Already have an account? ", text2 = "Log in", onCLick = {})
    
}

@Preview(showBackground = true)
@Composable
private fun TextWithMultipleStylesPreview() {
    MaterialTheme{
        Surface {
            TextWithMultipleStyles(
                fontSizeSp = 16,
                text1 = stringResource(id = R.string.already_have_account_part1),
                text2 = stringResource(id = R.string.already_have_account_part2),
                color2 = MaterialTheme.colorScheme.primary
            )
        }
    }
}
