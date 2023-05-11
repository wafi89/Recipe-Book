package asia.fourtitude.recipe.presentation.components.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import asia.fourtitude.recipe.presentation.components.textfield.TextFieldInputViewType.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldInputView(
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
    readOnly: Boolean = false,
    textFieldType: TextFieldInputViewType = LABEL,
    onDoneKeyboardActions : () -> Unit = {}
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    OutlinedTextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    // Scroll to make the TextField visible when it gains focus
                }
            },
        enabled = readOnly.not(),
        readOnly = readOnly,
        textStyle = when (textFieldType) {
            HEADER -> MaterialTheme.typography.headlineMedium
            BODY ->  MaterialTheme.typography.bodyMedium
            LABEL ->  MaterialTheme.typography.labelLarge
        },
        singleLine = false,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.DarkGray,
            unfocusedTextColor = if (readOnly) Color.White else Color.DarkGray,
            focusedContainerColor = Color.White.copy(0.8f),
            unfocusedContainerColor = if (readOnly) Color.Transparent else Color.White.copy(0.8f),
            disabledContainerColor = if (readOnly) Color.Transparent else Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedLeadingIconColor = Color.DarkGray,
            unfocusedLeadingIconColor = Color.DarkGray,
            focusedPlaceholderColor = Color.DarkGray,
            unfocusedPlaceholderColor = Color.DarkGray,
            disabledBorderColor = Color.Transparent,
            disabledTextColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
                onDoneKeyboardActions()
            }
        ),
    )
}

enum class TextFieldInputViewType {
    HEADER,
    BODY,
    LABEL
}