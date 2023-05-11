package asia.fourtitude.recipe.presentation.screens.addrecipe.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import asia.fourtitude.recipe.data.model.recipe.RecipeType
import asia.fourtitude.recipe.constant.PaddingDimension

@Composable
fun RecipeTypeSelectionView(
    items : List<RecipeType>,
    selectedCategoryIndex : MutableState<Int>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
            .padding(PaddingDimension.medium)
    ) {
        ElevatedButton(onClick = { expanded = true }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = items[selectedCategoryIndex.value].name,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            }

        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(PaddingDimension.medium)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = s.name,
                            color = Color.Black
                        )
                    },
                    onClick = {
                        selectedCategoryIndex.value = index
                        expanded = false
                    }
                )
            }
        }
    }
}