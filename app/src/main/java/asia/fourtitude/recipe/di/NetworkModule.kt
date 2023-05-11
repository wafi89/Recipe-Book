package asia.fourtitude.recipe.di

import android.content.Context
import asia.fourtitude.recipe.BuildConfig
import asia.fourtitude.recipe.data.RecipeApi
import asia.fourtitude.recipe.utils.interceptor.NoConnectionInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val requestTimeout = 60
        val loggingInterceptor = HttpLoggingInterceptor()
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)

        httpClient.addInterceptor(NoConnectionInterceptor(context))

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        httpClient.addInterceptor(loggingInterceptor)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideUnsplashRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.UNSPLASH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
        return builder.client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideFourtitudeRecipe(retrofit: Retrofit): RecipeApi {
        return retrofit.create(RecipeApi::class.java)
    }

}