package asia.fourtitude.recipe.data.model.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    var featuredImageUrl : String,
    val categories: List<RecipeType>,
    var description: String,
    val prepTimeInMillis : Long = 30000,
    val cookTimeInMillis : Long = 60000,
    var ingredients : MutableList<String>,
    var steps : MutableList<String>,
)

enum class RecipeCategory(val id : String, val value : String) {
    NONE("100","None"),
    BEEF("1","Beef"),
    CHICKEN("2","Chicken"),
    DESSERT("3","Dessert"),
    LAMB("4","Lamb"),
    PASTA("5","Pasta"),
    SEAFOOD("6","Seafood"),
    BREAKFAST("7","Breakfast"),
    MISC("8","Miscellaneous"),
}