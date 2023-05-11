package asia.fourtitude.recipe.presentation.screens.recipe.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import asia.fourtitude.recipe.R
import asia.fourtitude.recipe.constant.PaddingDimension
import asia.fourtitude.recipe.presentation.extensions.advancedShadow
import asia.fourtitude.recipe.presentation.extensions.pressClickEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewRecipeCardView(
    onCardClick : () -> Unit
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = PaddingDimension.medium)
            .height(200.dp)
            .advancedShadow(
                color = Color(0xff03EF9B),
                alpha = 0.5f,
                shadowBlurRadius = 10.dp,
                offsetY = (-8).dp,
                cornersRadius = 24.dp,
            )
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .pressClickEffect(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.Black.copy(0.6f)
        ),
        onClick = {
            onCardClick()
        }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.img_wave),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    modifier = Modifier.size(72.dp),
                    painter = painterResource(id = R.drawable.ic_action_add_recipe),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(PaddingDimension.medium),
                    text = stringResource(R.string.recipe_add_new_recipe),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}