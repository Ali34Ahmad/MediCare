package com.example.doctor.core.composables

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.doctor.R
import com.example.doctor.ui.theme.Spacing
import com.example.doctor.ui.theme.imageMaskColor

@Composable
fun ImagePickerDialog(
    errorMessage: Int?,
    explanationText: Int,
    onSaveButtonClick: () -> Unit,
    updateImageUri: (Uri?) -> Unit,
    imageUri: Uri?,
    updateBitmap: (Bitmap?) -> Unit,
    bitmap: Bitmap?,
    onDismissRequest: () -> Unit,
    showProgressBar:Boolean,
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        ImagePickerComponent(
            errorMessage = errorMessage,
            onSaveButtonClick = onSaveButtonClick,
            explanationText = explanationText,
            updateImageUri = updateImageUri,
            imageUri = imageUri,
            updateBitmap = updateBitmap,
            bitmap = bitmap,
            showProgressBar=showProgressBar,
        )
    }
}

@Composable
fun ImagePickerComponent(
    errorMessage: Int?,
    explanationText: Int,
    onSaveButtonClick: () -> Unit,
    updateImageUri: (Uri?) -> Unit,
    imageUri: Uri?,
    bitmap: Bitmap?,
    updateBitmap: (Bitmap) -> Unit,
    showProgressBar:Boolean
) {
    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            updateImageUri(uri)
        }

    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            updateBitmap(MediaStore.Images.Media.getBitmap(context.contentResolver, it))
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            updateBitmap(ImageDecoder.decodeBitmap(source))
        }
    }


    Card(
        modifier = Modifier
            .height(400.dp)
            .width(350.dp)
    ) {
        if (bitmap != null) {
            ShowChosenImageComponent(
                bitmap = bitmap,
                errorMessage = errorMessage,
                onResetButtonClick = { launcher.launch("image/*") },
                onSaveButtonClick = onSaveButtonClick,
                showProgressBar=showProgressBar
            )
        } else {
            UploadImageComponent(
                onUploadClick = {
                    launcher.launch("image/*")
                },
                explanationText = explanationText,
            )
        }
    }
}

@Composable
fun ShowChosenImageComponent(
    bitmap: Bitmap,
    onResetButtonClick: () -> Unit,
    errorMessage: Int?,
    onSaveButtonClick: () -> Unit,
    showProgressBar:Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = Spacing.large, vertical = Spacing.medium
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (errorMessage != null) Text(text = stringResource(id = errorMessage))
        Box(contentAlignment = Alignment.Center) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .width(268.dp)
                    .height(268.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )
            if (showProgressBar){
                Spacer(
                    modifier = Modifier
                        .width(268.dp)
                        .height(268.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(color = imageMaskColor),
                )
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(60.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacing.medium))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButtonComponent(
                text = R.string.reset,
                onClick = onResetButtonClick,
                isDisabled=showProgressBar,
            )
            ElevatedButtonComponent(
                text = R.string.save,
                onClick = onSaveButtonClick,
                isDisabled=showProgressBar,
            )
        }

    }
}

@Composable
fun UploadImageComponent(
    onUploadClick: () -> Unit, explanationText: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Outlined.Photo,
            contentDescription = null,
            modifier = Modifier
                .height(Spacing.extraLarge)
                .width(Spacing.extraLarge)
        )
        Text(text = stringResource(id = explanationText))
        Spacer(modifier = Modifier.height(Spacing.medium))
        ElevatedButtonComponent(
            text = R.string.upload, onClick = onUploadClick, isDisabled = false
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ImagePickerComponentPreview() {
    MaterialTheme {
        Surface {
            ImagePickerComponent(onSaveButtonClick = {},
                explanationText = R.string.upload_a_profile_photo,
                errorMessage = null,
                imageUri = Uri.EMPTY,
                updateImageUri = {},
                bitmap = null,
                updateBitmap = {},
                showProgressBar=false,
            )
        }
    }
}



