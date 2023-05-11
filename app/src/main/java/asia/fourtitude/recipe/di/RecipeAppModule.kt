package asia.fourtitude.recipe.di

import android.app.Application
import androidx.room.Room
import asia.fourtitude.recipe.data.dao.RecipeDao
import asia.fourtitude.recipe.data.db.RecipeDatabase
import asia.fourtitude.recipe.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeAppModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): RecipeDatabase {
        return Room.databaseBuilder(
            application,
            RecipeDatabase::class.java,
            "recipe-database"
        ).build()
    }

    @Provides
    fun provideRecipeDao(database: RecipeDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(recipeDao: RecipeDao): RecipeRepository {
        return RecipeRepository(recipeDao)
    }
}