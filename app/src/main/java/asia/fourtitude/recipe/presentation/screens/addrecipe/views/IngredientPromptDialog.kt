package asia.fourtitude.recipe.presentation.screens.addrecipe.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import asia.fourtitude.recipe.R

@Composable
fun IngredientPromptDialog(onConfirm: (String) -> Unit, onCancel: () -> Unit) {

    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(stringResource(R.string.add_recipe_enter_ingredient)) },
        text = {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
        },
        confirmButton = {
            Button(
                enabled = text.isNotBlank(),
                onClick = { onConfirm(text) }
            ) {
                Text(stringResource(R.string.add_recipe_ok))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onCancel) {
                Text(stringResource(R.string.add_recipe_cancel))
            }
        }
    )
}