package asia.fourtitude.recipe.presentation.screens.recipedetail.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import asia.fourtitude.recipe.constant.PaddingDimension
import asia.fourtitude.recipe.data.model.recipe.Recipe
import asia.fourtitude.recipe.presentation.components.spacer.SpacerVerticalSmall
import asia.fourtitude.recipe.presentation.components.textfield.TextFieldInputView
import asia.fourtitude.recipe.presentation.components.textfield.TextFieldInputViewType
import coil.compose.AsyncImage

@Composable
fun RecipeDetailContent(
    featuredImageUrl: String,
    nameState: MutableState<TextFieldValue>,
    descriptionState: MutableState<TextFieldValue>,
    readOnly: Boolean,
    isPromptDelete : MutableState<Boolean>,
    recipe : Recipe,
    ingredients : List<String>,
    steps : List<String>,
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .blur(if (isPromptDelete.value) 10.dp else 0.dp),
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                model = featuredImageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Column(
                modifier = Modifier.padding(PaddingDimension.medium),
                verticalArrangement = Arrangement.spacedBy(PaddingDimension.small)
            ) {

                TextFieldInputView(
                    state = nameState,
                    readOnly = readOnly,
                    textFieldType = TextFieldInputViewType.HEADER
                )

                TextFieldInputView(
                    state = descriptionState,
                    readOnly = readOnly,
                    textFieldType = TextFieldInputViewType.BODY
                )
            }

            Text(
                modifier = Modifier.padding(16.dp),
                text = "Ingredients",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            ingredients.forEachIndexed { index, text ->

                val ingredientState = remember { mutableStateOf(TextFieldValue(text)) }
                recipe.ingredients[index] = ingredientState.value.text

                Column() {
                    Row(
                        modifier = Modifier.padding(horizontal = PaddingDimension.medium),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimatedVisibility(visible = readOnly) {
                            Text(
                                text = "${index + 1}.",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        TextFieldInputView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 8.dp),
                            state = ingredientState,
                            readOnly = readOnly
                        )
                    }

                    AnimatedVisibility(visible = readOnly.not()) {
                        SpacerVerticalSmall()
                    }
                }
            }

            Text(
                modifier = Modifier.padding(16.dp),
                text = "Steps",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            steps.forEachIndexed { index, text ->
                val stepState = mutableStateOf(TextFieldValue(text))
                recipe.steps[index] = stepState.value.text

                Column() {
                    Row(
                        modifier = Modifier.padding(horizontal = PaddingDimension.medium),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimatedVisibility(visible = readOnly) {
                            Text(
                                text = "${index + 1}.",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        TextFieldInputView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 8.dp),
                            state = stepState,
                            readOnly = readOnly,
                        )
                    }
                    AnimatedVisibility(visible = readOnly.not()) {
                        SpacerVerticalSmall()
                    }
                }
            }

        }

        if (isPromptDelete.value) {
            Box(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(Color.Transparent)
            )
        }
    }
}