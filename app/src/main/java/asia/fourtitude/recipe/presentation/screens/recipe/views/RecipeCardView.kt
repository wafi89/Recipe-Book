package asia.fourtitude.recipe.presentation.screens.recipe.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import asia.fourtitude.recipe.R
import asia.fourtitude.recipe.data.model.recipe.Recipe
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeCardView(
    item  : Recipe,
    onCardClick : (Long) -> Unit
){
    Column() {
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            onClick = {
                onCardClick(item.id)
            }
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = item.featuredImageUrl,
                placeholder = painterResource(id = R.drawable.img_placeholder_potrait),
                contentDescription = stringResource(R.string.content_description_recipe_image),
                contentScale = ContentScale.FillWidth
            )
        }

        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}