package asia.fourtitude.recipe.presentation.screens.recipedetail.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import asia.fourtitude.recipe.constant.RecipeAction
import asia.fourtitude.recipe.presentation.screens.appbar.MainTopBarViewModel
import asia.fourtitude.recipe.constant.PaddingDimension

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun RecipeDetailTopAppBar(
    modifier: Modifier = Modifier,
    isOnEditMode: MutableState<Boolean>,
    isPromptDelete: MutableState<Boolean>,
    recipeName: String,
    onNavigateUpClick: () -> Unit,
    onDoneClick: () -> Unit,
    onMoreOptionsClick: () -> Unit,
    onDeleteClick: () -> Unit,
    viewModel: MainTopBarViewModel = hiltViewModel(),
) {

    val menuItems by remember {
        mutableStateOf(
            listOf(
                RecipeAction.EDIT,
                RecipeAction.DELETE
            )
        )
    }
    val selectedMenuItem = remember { mutableStateOf(menuItems[0]) }
    val isMenusExpanded = remember { mutableStateOf(false) }

    Column() {
        CenterAlignedTopAppBar(
            navigationIcon = {
                if (isOnEditMode.value) {
                    OutlinedButton(
                        onClick = {
                            isOnEditMode.value = false
                        }
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                } else if (isPromptDelete.value) {
                    OutlinedButton(
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White
                        ),
                        onClick = {
                            isPromptDelete.value = false
                        }
                    ) {
                        Text(
                            text = "No, cancel",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            onNavigateUpClick()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                }
            },
            title = {
                if (isOnEditMode.value) {
                    Text(
                        text = "Edit Recipe",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            actions = {
                Box {
                    if (isOnEditMode.value) {
                        Button(
                            onClick = {
                                onDoneClick()
                            }
                        ) {
                            Text(
                                text = "Done",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    } else if (isPromptDelete.value) {
                        Button(
                            colors = ButtonDefaults.buttonColors(

                            ),
                            onClick = {
                                onDeleteClick()
                            }
                        ) {
                            Text(
                                text = "Confirm delete",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                isMenusExpanded.value = isMenusExpanded.value.not()
                                onMoreOptionsClick()
                            }
                        ) {
                            Image(
                                imageVector = Icons.Outlined.MoreVert,
                                contentDescription = "more icon",
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }
                    }

                    MaterialTheme(
                        shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp)),
                        colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White)
                    ) {
                        DropdownMenu(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .align(Alignment.TopEnd),
                            expanded = isMenusExpanded.value,
                            onDismissRequest = { isMenusExpanded.value = false },
                        ) {
                            menuItems.forEach { item ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = item.label,
                                            color = if (item == RecipeAction.DELETE) Color(
                                                0xFFED5F68
                                            ) else Color.Black,
                                            fontWeight = FontWeight.Bold
                                        )
                                    },
                                    onClick = {
                                        selectedMenuItem.value = item
                                        isMenusExpanded.value = false
                                        when (item) {
                                            RecipeAction.EDIT -> {
                                                isOnEditMode.value = true
                                            }

                                            RecipeAction.DELETE -> {
                                                isPromptDelete.value = true
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = if (isPromptDelete.value) Color(0xFFED5F68) else MaterialTheme.colorScheme.background,
                titleContentColor = AlertDialogDefaults.titleContentColor,
                navigationIconContentColor = AlertDialogDefaults.titleContentColor,
            ),
        )

        AnimatedVisibility(
            visible = isOnEditMode.value,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background,
                contentColor = Color.White,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingDimension.medium),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Outlined.Info, contentDescription = null
                    )

                    Text(
                        text = "Click any text box to edit, then click Done to save. Or you can click Cancel to discard the changes.",
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }
        }


        AnimatedVisibility(
            visible = isPromptDelete.value,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFED5F68),
                contentColor = Color.White,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ) {
                Column() {

                    val gradientColors = listOf(Color.Cyan, Color.Magenta, Color.Yellow)


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(PaddingDimension.medium),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Outlined.Warning, contentDescription = null
                        )

                        Text(
                            text = buildAnnotatedString {
                                append("Are you sure want to delete ")
                                withStyle(
                                    SpanStyle(
                                        brush = SolidColor(Color.Black),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                ) {
                                    append(recipeName)
                                }
                                append(" recipe? This process cannot be undone.\n\n To proceed, press Confirm Delete")
                            },
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.bodyLarge
                        )

                    }
                }
            }
        }

    }
}


