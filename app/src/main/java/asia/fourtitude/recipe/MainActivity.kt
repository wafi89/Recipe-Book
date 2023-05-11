package asia.fourtitude.recipe

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import asia.fourtitude.recipe.presentation.screens.main.MainScreenView
import asia.fourtitude.recipe.theme.RecipeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController()
            RecipeTheme(useDarkTheme = true) {
                MainScreenView(
                    navController = navController,
                )
            }
        }
    }

}

