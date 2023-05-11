package asia.fourtitude.recipe.presentation.components.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerVerticalExtraSuperLarge() {
    Spacer(modifier = Modifier.height(120.dp))
}

@Composable
fun SpacerVerticalSuperLarge() {
    Spacer(modifier = Modifier.height(60.dp))
}

@Composable
fun SpacerVerticalLarge() {
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun SpacerVerticalMedium() {
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun SpacerVerticalSmall() {
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun SpacerHorizontalVerySmall() {
    Spacer(modifier = Modifier.width(6.dp))
}

@Composable
fun SpacerHorizontalSmall() {
    Spacer(modifier = Modifier.width(12.dp))
}
