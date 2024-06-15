package com.example.medicare.presentation.children

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.medicare.R
import com.example.medicare.core.composables.ErrorDialog
import com.example.medicare.core.composables.LoadingDialog
import com.example.medicare.data.model.child.Child
import com.example.medicare.ui.composables.AddedChildrenList
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChildrenScreen(
    onAddChildClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChildrenViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val children: List<Child> =
        viewModel.children.collectAsStateWithLifecycle(initialValue = emptyList()).value

    val coroutineScope = rememberCoroutineScope()

    Spacer(modifier = Modifier.height(Spacing.medium))

    LoadingDialog(showDialog = uiState.value.showLoadingDialog)
    ErrorDialog(showDialog = uiState.value.showErrorDialog,
        onDismissRequest = {
            viewModel.updateErrorDialogVisibilityState()
        }) {

    }

    LaunchedEffect(key1 = children) {
        coroutineScope.launch {
            delay(3000)
            viewModel.updateNoChildAddedYetState()
        }
    }
    if(children.isEmpty()&&uiState.value.showNoChildAdded) {
        NoChildrenAddedYet(onAddChildClick = onAddChildClick)
        viewModel.updateLoadingDialogVisibilityState(false)
    }
    else {
        AddedChildrenList(
            children = children,
            modifier = modifier.padding(horizontal = Spacing.medium)
        )
        viewModel.updateLoadingDialogVisibilityState(false)
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
            ChildrenScreen(onAddChildClick = {})
        }
    }
}