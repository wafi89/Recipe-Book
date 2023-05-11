package asia.fourtitude.recipe.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import asia.fourtitude.recipe.data.dao.RecipeDao
import asia.fourtitude.recipe.data.model.recipe.Recipe
import asia.fourtitude.recipe.utils.database.ListStringConverter
import asia.fourtitude.recipe.utils.database.RecipeTypeListConverter

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(ListStringConverter::class, RecipeTypeListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}