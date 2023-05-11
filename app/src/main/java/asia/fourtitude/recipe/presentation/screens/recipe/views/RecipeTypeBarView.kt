package asia.fourtitude.recipe.presentation.screens.recipe.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import asia.fourtitude.recipe.data.model.recipe.RecipeCategory
import asia.fourtitude.recipe.data.model.recipe.RecipeType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeTypeBarView(
    modifier: Modifier = Modifier,
    items: List<RecipeType>,
    onFilterClick: (RecipeType) -> Unit
) {

    val state = rememberLazyListState()
    var selectedChipIndex by remember { mutableStateOf(-1) }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(12.dp),
        state = state,
    ) {
        items(
            count = items.size,
            key = {
                items[it].id
            },
            itemContent = { index ->
                ElevatedFilterChip(
                    selected = index == selectedChipIndex,
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        containerColor = Color.White,
                        selectedContainerColor = MaterialTheme.colorScheme.inversePrimary,
                        labelColor = Color.Black,
                        selectedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    shape = RoundedCornerShape(50),
                    onClick = {
                        selectedChipIndex = if (selectedChipIndex == index) -1 else index
                        if (selectedChipIndex == -1) {
                            onFilterClick(
                                RecipeType(
                                    RecipeCategory.NONE.id,
                                    RecipeCategory.NONE.name,
                                )
                            )
                        } else {
                            onFilterClick(items[selectedChipIndex])
                        }
                    },
                    label = { Text(items[index].name) },
                )
            }
        )
    }

}