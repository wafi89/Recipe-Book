package asia.fourtitude.recipe.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import asia.fourtitude.recipe.presentation.navigation.NavGraphMain
import asia.fourtitude.recipe.presentation.navigation.NavScreen
import asia.fourtitude.recipe.presentation.navigation.NavigationRouteUtils
import asia.fourtitude.recipe.presentation.screens.appbar.MainTopAppBar
import asia.fourtitude.recipe.utils.network.ConnectionState
import asia.fourtitude.recipe.utils.network.connectivityState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScreenView(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel(),
) {

    val primaryBackgroundColor = MaterialTheme.colorScheme.primary
    val statusBarColor = MaterialTheme.colorScheme.background
    val systemBarColor = MaterialTheme.colorScheme.background
    val backgroundColor = MaterialTheme.colorScheme.background
    val contentColor = Color.White

    // STATE CONTROLS
    val systemUiController = rememberSystemUiController()
    val screenRoute = rememberSaveable { mutableStateOf(NavScreen.Recipe.route) }
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val shouldNavigateUp = rememberSaveable { (mutableStateOf(false)) }
    val isPromptDelete = remember { mutableStateOf(false) }

    // CONNECTIVITY
    val connection by connectivityState()
    val isNetworkConnected = connection === ConnectionState.Available

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if(isPromptDelete.value) Color(0xFFED5F68) else statusBarColor,
            darkIcons = backgroundColor == primaryBackgroundColor
        )
        systemUiController.setNavigationBarColor(color = systemBarColor)
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            screenRoute.value = backStackEntry.destination.route ?: NavScreen.Recipe.route
            when (screenRoute.value) {
                NavScreen.Recipe.route -> {
                    topBarState.value = true
                    bottomBarState.value = false
                    shouldNavigateUp.value = false
                }

                else -> {
                    topBarState.value = false
                    bottomBarState.value = false
                    shouldNavigateUp.value = true
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor,
        contentColor = contentColor
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = Color.White,
            topBar = {
                MainTopAppBar(
                    titleContentColor = contentColor,
                    navigationIconContentColor = contentColor,
                    screenRouteTitle = NavigationRouteUtils.getTitleFromNavRoute(screenRoute.value),
                    showTopBar = topBarState.value,
                )
            },
        ) { innerPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                // NAVIGATION GRAPH
                NavGraphMain(
                    navController = navController,
                    isPromptDelete = isPromptDelete,
                )
            }
        }
    }
}