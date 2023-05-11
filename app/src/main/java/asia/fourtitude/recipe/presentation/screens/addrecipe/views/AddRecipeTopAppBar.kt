package asia.fourtitude.recipe.presentation.screens.addrecipe.views

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import asia.fourtitude.recipe.presentation.animation.AnimatedVisibilityParallax
import asia.fourtitude.recipe.presentation.screens.appbar.MainTopBarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeTopAppBar(
    modifier: Modifier = Modifier,
    enableDoneButton: Boolean,
    onCancelClick: () -> Unit,
    onDoneClick: () -> Unit,
    viewModel: MainTopBarViewModel = hiltViewModel(),
) {
    AnimatedVisibilityParallax(visible = true) {
        CenterAlignedTopAppBar(
            navigationIcon = {
                OutlinedButton(
                    onClick = {
                        onCancelClick()
                    }
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            title = {
                Text(
                    text = "Add Recipe",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            actions = {
                Button(
                    enabled = enableDoneButton,
                    onClick = {
                        onDoneClick()
                    }
                ) {
                    Text(
                        text = "Done",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background.copy(0.9f),
                titleContentColor = AlertDialogDefaults.titleContentColor,
                navigationIconContentColor = AlertDialogDefaults.titleContentColor,
            ),
        )
    }
}


