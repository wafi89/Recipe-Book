package asia.fourtitude.recipe.data

import asia.fourtitude.recipe.data.model.search.SearchResponse
import retrofit2.Response
import retrofit2.http.*

interface RecipeApi {

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("client_id") apiKey: String
    ): Response<SearchResponse>

}