package asia.fourtitude.recipe.presentation.screens.addrecipe


import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import asia.fourtitude.recipe.R
import asia.fourtitude.recipe.data.model.recipe.Recipe
import asia.fourtitude.recipe.presentation.animation.AnimatedVisibilityParallax
import asia.fourtitude.recipe.presentation.components.spacer.SpacerVerticalMedium
import asia.fourtitude.recipe.presentation.screens.addrecipe.views.AddRecipeTopAppBar
import asia.fourtitude.recipe.presentation.screens.addrecipe.views.AddedRecipeSuccessfulDialog
import asia.fourtitude.recipe.presentation.screens.addrecipe.views.IngredientPromptDialog
import asia.fourtitude.recipe.presentation.screens.addrecipe.views.RecipeTypeSelectionView
import asia.fourtitude.recipe.presentation.screens.addrecipe.views.StepPromptDialog
import asia.fourtitude.recipe.presentation.components.textfield.TextFieldInputView
import asia.fourtitude.recipe.utils.file.FileUtils
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddRecipeScreen(
    navController: NavController,
    viewModel: AddRecipeViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/anim_cooking.json"))
    val nameState = remember { mutableStateOf(TextFieldValue("")) }
    val descriptionState = remember { mutableStateOf(TextFieldValue("")) }
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    var showAddIngredientDialog by remember { mutableStateOf(false) }
    var showAddStepDialog by remember { mutableStateOf(false) }
    var showSuccessAddedRecipe by remember { mutableStateOf(false) }
    var showPhotoRefreshLoading by remember { mutableStateOf(false) }
    val ingredients = remember { mutableStateListOf<String>() }
    val steps = remember { mutableStateListOf<String>() }
    val context = LocalContext.current
    val inputStream = context.resources.openRawResource(R.raw.recipetypes)
    val xmlString = inputStream.bufferedReader().use { it.readText() }
    val recipeTypes = FileUtils.parseRecipeTypesFromXml(xmlString)
    val selectedCategoryIndex = remember { mutableStateOf(0) }
    val validateImage =
        viewState.photos.isNotEmpty() && viewState.photos.first().urls.regular.isNotBlank()
    val validateName = nameState.value.text.isNotBlank()
    val validateDescription = descriptionState.value.text.isNotBlank()
    val validateIngredients = ingredients.isNotEmpty()
    val validateSteps = steps.isNotEmpty()

    LaunchedEffect(showPhotoRefreshLoading) {
        if (showPhotoRefreshLoading) {
            viewModel.getSearchItems(
                nameState.value.text,
                onCompleteRequest = { success ->
                    if (success) {
                        Toast.makeText(context, "Success get recipe image", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Something when wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column {
            AddRecipeTopAppBar(
                enableDoneButton = validateImage &&
                        validateName &&
                        validateDescription &&
                        validateIngredients &&
                        validateSteps,
                onCancelClick = {
                    navController.popBackStack()
                },
                onDoneClick = {
                    scope.launch {
                        viewModel.addRecipe(
                            recipe = Recipe(
                                name = nameState.value.text,
                                featuredImageUrl = viewState.photos.first().urls.regular,
                                categories = listOf(recipeTypes[selectedCategoryIndex.value]),
                                description = descriptionState.value.text,
                                ingredients = ingredients,
                                steps = steps,
                            )
                        )
                        showSuccessAddedRecipe = true
                        delay(1_250)
                        showSuccessAddedRecipe = false
                        navController.popBackStack()
                    }

                }
            )

            Divider()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
            ) {

                AnimatedVisibilityParallax(visible = viewState.photos.isEmpty()) {
                    LottieAnimation(
                        modifier = Modifier
                            .height(200.dp)
                            .align(Alignment.CenterHorizontally),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        contentScale = ContentScale.FillHeight
                    )
                }

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Photo",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                Box {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(100.dp),
                        model = viewState.photos.firstOrNull()?.urls?.regular,
                        contentDescription = null
                    )
                    if (viewState.photos.isEmpty()) {
                        Column(Modifier.align(Alignment.BottomStart)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                Image(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .size(60.dp),
                                    imageVector = Icons.Default.PhotoLibrary,
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                                FilledTonalButton(
                                    enabled = nameState.value.text.isNotBlank(),
                                    onClick = {
                                        showPhotoRefreshLoading = true
                                    }
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(16.dp),
                                        text = "Refresh photo...",
                                        style = MaterialTheme.typography.titleMedium,
                                    )
                                    if (showPhotoRefreshLoading) {
                                        CircularProgressIndicator()
                                    } else {
                                        Icon(
                                            imageVector = Icons.Outlined.Refresh,
                                            contentDescription = null
                                        )
                                    }

                                }
                            }
                            Text(
                                modifier = Modifier
                                    .padding(16.dp),
                                text = "Recipe image will be generated automatically after you enter a valid recipe's name",
                                style = MaterialTheme.typography.labelSmall,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }

                Divider()

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Recipe name",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                TextFieldInputView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    state = nameState,
                    onDoneKeyboardActions = {
                        viewModel.getSearchItems(
                            nameState.value.text,
                            onCompleteRequest = {

                            }
                        )
                    }
                )

                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Give your recipe a good name, so others will know it's delicious even without seeing the photo",
                    style = MaterialTheme.typography.labelSmall,
                    fontStyle = FontStyle.Italic
                )

                Divider()

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                TextFieldInputView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    state = descriptionState,
                )

                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Short description of your recipe does not always means it not delicious, but long one can include more details",
                    style = MaterialTheme.typography.labelSmall,
                    fontStyle = FontStyle.Italic
                )

                Divider()

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Type",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                RecipeTypeSelectionView(
                    items = recipeTypes,
                    selectedCategoryIndex = selectedCategoryIndex
                )

                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Select a type of your recipe, so later anyone can quickly filter their favourite one!",
                    style = MaterialTheme.typography.labelSmall,
                    fontStyle = FontStyle.Italic
                )

                Divider()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Ingredients",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Button(onClick = { showAddIngredientDialog = true }) {
                        Row() {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                            Text(
                                text = "Add",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                }

                ingredients.forEachIndexed { index, s ->
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                    ) {

                        OutlinedButton(
                            modifier = Modifier.padding(12.dp),
                            shape = RoundedCornerShape(8.dp),
                            onClick = {

                            }
                        ) {
                            Text(text = ingredients[index])
                        }

                        OutlinedButton(
                            onClick = { ingredients.removeAt(index) },
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.TopEnd),
                            shape = CircleShape,
                            border = BorderStroke(1.dp, Color.Red),
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White,
                            )
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }

                    }
                }

                Divider()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Steps",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Button(
                        onClick = {
                            showAddStepDialog = true
                        }
                    ) {
                        Row() {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                            Text(
                                text = "Add",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                steps.forEachIndexed { index, s ->

                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                    ) {

                        OutlinedButton(
                            modifier = Modifier.padding(12.dp),
                            shape = RoundedCornerShape(8.dp),
                            onClick = {

                            }
                        ) {
                            Text(text = steps[index])
                        }

                        OutlinedButton(
                            onClick = { steps.removeAt(index) },
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.TopEnd),
                            shape = CircleShape,
                            border = BorderStroke(1.dp, Color.Red),
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White,
                            )
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }

                    }
                }

                Divider()

                SpacerVerticalMedium()
            }
        }

        if (showAddIngredientDialog) {
            IngredientPromptDialog(
                onConfirm = { text ->
                    // Handle OK button click here
                    showAddIngredientDialog = false
                    ingredients.add(text)
                },
                onCancel = {
                    // Handle Cancel button click here
                    showAddIngredientDialog = false
                }
            )
        }

        if (showAddStepDialog) {
            StepPromptDialog(
                onConfirm = { text ->
                    // Handle OK button click here
                    showAddStepDialog = false
                    steps.add(text)
                },
                onCancel = {
                    // Handle Cancel button click here
                    showAddStepDialog = false
                }
            )
        }

        AnimatedVisibility(
            visible = showSuccessAddedRecipe,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            AddedRecipeSuccessfulDialog()
        }

    }

}









