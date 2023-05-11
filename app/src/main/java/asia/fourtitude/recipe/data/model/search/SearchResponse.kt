package asia.fourtitude.recipe.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results")
    val results: List<Photo>
)

data class Photo(
    @SerializedName("urls")
    val urls: Urls
)

data class Urls(
    @SerializedName("regular")
    val regular: String
)

