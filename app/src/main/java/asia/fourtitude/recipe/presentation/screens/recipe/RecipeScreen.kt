package asia.fourtitude.recipe.presentation.screens.recipe


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asia.fourtitude.recipe.R
import asia.fourtitude.recipe.constant.PaddingDimension
import asia.fourtitude.recipe.data.model.recipe.RecipeCategory
import asia.fourtitude.recipe.data.model.recipe.RecipeType
import asia.fourtitude.recipe.prefs.SessionManagerUtils
import asia.fourtitude.recipe.presentation.navigation.NavScreen
import asia.fourtitude.recipe.presentation.screens.recipe.views.AddNewRecipeCardView
import asia.fourtitude.recipe.presentation.screens.recipe.views.RecipeCardView
import asia.fourtitude.recipe.presentation.screens.recipe.views.RecipeTypeBarView
import asia.fourtitude.recipe.utils.file.FileUtils.parseRecipeTypesFromXml
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RecipeScreen(
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val inputStream = context.resources.openRawResource(R.raw.recipetypes)
    val xmlString = inputStream.bufferedReader().use { it.readText() }
    val recipeTypes = parseRecipeTypesFromXml(xmlString)
    var recipeType by remember {
        mutableStateOf(
            RecipeType(
                id = RecipeCategory.NONE.id,
                name = RecipeCategory.NONE.name,
            )
        )
    }
    val allRecipes = viewModel.recipes.collectAsState(emptyList())
    val recipes = viewModel.recipes.collectAsState(emptyList()).value.filter { recipe ->
        if (recipeType.id == RecipeCategory.NONE.id) {
            true
        } else {
            recipe.categories.first().id == recipeType.id
        }
    }

    if (SessionManagerUtils.getFirstTimeInstall(context)) {
        viewModel.initializeRecipes()
        SessionManagerUtils.setFirstTimeInstall(context, false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            if (allRecipes.value.isNotEmpty()) {
                RecipeTypeBarView(
                    items = recipeTypes,
                    onFilterClick = { type ->
                        recipeType = type
                    }
                )
            }

            if (recipes.isNotEmpty()) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = PaddingDimension.medium),
                    verticalItemSpacing = PaddingDimension.medium,
                    horizontalArrangement = Arrangement.spacedBy(PaddingDimension.medium)
                ) {
                    items(
                        count = recipes.size,
                        key = {
                            recipes[it].id
                        }
                    ) { index ->
                        val item = recipes[index]
                        RecipeCardView(
                            item = item,
                            onCardClick = {id ->
                                navController.navigate(NavScreen.RecipeDetail.passRecipeId(id))
                            }
                        )
                    }

                    if (recipeType.id == RecipeCategory.NONE.id) {
                        item {
                            AddNewRecipeCardView(
                                onCardClick = {
                                    navController.navigate(NavScreen.AddRecipe.route)
                                }
                            )
                        }
                    }
                }
            } else if (allRecipes.value.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingDimension.large),
                    text = "No recipe found for '${recipeType.name}'",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
            } else {
                Box(Modifier.fillMaxSize()){
                    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/anim_empty_recipe.json"))
                    LottieAnimation(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .align(Alignment.Center),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
        }

        if (allRecipes.value.isEmpty()) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(PaddingDimension.medium),
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                onClick = { navController.navigate(NavScreen.AddRecipe.route) }
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_add_recipe),
                        contentDescription = null
                    )

                    Text(
                        text = stringResource(R.string.recipe_add_new_recipe),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }

            }
        }
    }
}

