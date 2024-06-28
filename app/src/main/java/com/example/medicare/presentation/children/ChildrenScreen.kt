package com.example.medicare.presentation.children

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.medicare.R
import com.example.medicare.core.composables.ErrorDialog
import com.example.medicare.core.composables.LoadingDialog
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.data.model.child.Child
import com.example.medicare.ui.composables.AddedChildrenList
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChildrenScreen(
    navigateToAddChildScreen: () -> Unit,
    onNotificationButtonClick: () -> Unit,
    onChildCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    uiState: ChildrenUiState,
    children:List<Child>,
    updateErrorDialogVisibilityState:(Boolean)->Unit,
    updateNoChildAddedYetState:(Boolean)->Unit,
    updateLoadingDialogVisibilityState:(Boolean)->Unit,
) {

    val coroutineScope = rememberCoroutineScope()


    LoadingDialog(
        showDialog = uiState.showLoadingDialog
    )
    ErrorDialog(
        showDialog = uiState.showErrorDialog,
        onDismissRequest = {
            updateErrorDialogVisibilityState(false)
        }
    ) {

    }

    LaunchedEffect(key1 = children) {
        coroutineScope.launch {
            delay(3000)
            updateNoChildAddedYetState(true)
        }
    }

    Scaffold(
        topBar = {
            MedicareTopAppBar(
                showNavigateUpIconButton = false,
                showNotificationIconButton = true,
                title = R.string.app_name,
                onNotificationClick = onNotificationButtonClick
            )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            if (children.isEmpty() && uiState.showNoChildAdded) {
                NoChildrenAddedYet(onAddChildClick = navigateToAddChildScreen)
                updateLoadingDialogVisibilityState(false)
            } else {
                AddedChildrenList(
                    children = children,
                    onChildCardClick = onChildCardClick,
                    modifier = modifier.padding(Spacing.medium),
                )
                updateLoadingDialogVisibilityState(false)
            }
        }
    }
}


@Composable
fun NoChildrenAddedYet(
    onAddChildClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ill_family_children),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.no_children_added),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(Spacing.medium))
        ElevatedButtonComponent(
            text = R.string.add_child,
            onClick = onAddChildClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChildrenScreenPreview() {
    MediCareTheme {
        Surface {
            ChildrenScreen(
                navigateToAddChildScreen = {},
                onChildCardClick = {},
                onNotificationButtonClick = {},
                children = listOf(Child(firstName = "Ali", lastName = "Ahmad")),
                uiState = ChildrenUiState(),
                updateErrorDialogVisibilityState = {},
                updateLoadingDialogVisibilityState = {},
                updateNoChildAddedYetState = {}
            )
        }
    }
}