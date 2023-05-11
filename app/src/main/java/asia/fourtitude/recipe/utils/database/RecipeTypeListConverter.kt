package asia.fourtitude.recipe.utils.database

import androidx.room.TypeConverter
import asia.fourtitude.recipe.data.model.recipe.RecipeType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeTypeListConverter {
    @TypeConverter
    fun fromCustomObjectList(customObjectList: List<RecipeType>): String {
        val gson = Gson()
        return gson.toJson(customObjectList)
    }

    @TypeConverter
    fun toCustomObjectList(customObjectListString: String): List<RecipeType> {
        val gson = Gson()
        val type = object : TypeToken<List<RecipeType>>() {}.type
        return gson.fromJson(customObjectListString, type)
    }
}