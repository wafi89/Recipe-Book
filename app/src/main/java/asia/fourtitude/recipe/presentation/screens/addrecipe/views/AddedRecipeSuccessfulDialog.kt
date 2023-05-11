package asia.fourtitude.recipe.presentation.screens.addrecipe.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import asia.fourtitude.recipe.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AddedRecipeSuccessfulDialog(
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/anim_successful_topup.json"))

    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),
        onDismissRequest = {
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                )
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = stringResource(R.string.add_recipe_successfully_added_recipe),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

            }

        }
    }
}