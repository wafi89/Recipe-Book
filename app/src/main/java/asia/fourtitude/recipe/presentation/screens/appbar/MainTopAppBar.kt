package asia.fourtitude.recipe.presentation.screens.appbar

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import asia.fourtitude.recipe.presentation.animation.AnimatedVisibilityParallax

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    titleContentColor: Color,
    navigationIconContentColor: Color,
    @StringRes screenRouteTitle: Int,
    showTopBar : Boolean,
    viewModel: MainTopBarViewModel = hiltViewModel(),
) {
    AnimatedVisibilityParallax(visible = showTopBar) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = screenRouteTitle),
                    color = MaterialTheme.colorScheme.tertiary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = titleContentColor,
                navigationIconContentColor = navigationIconContentColor,
            ),
        )
    }
}


