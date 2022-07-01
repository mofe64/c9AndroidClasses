package com.nubari.groceries.di

import android.app.Application
import androidx.room.Room
import com.nubari.groceries.data.local.GroceriesDatabase
import com.nubari.groceries.data.remote.Api
import com.nubari.groceries.data.repositories.GroceryRepositoryImpl
import com.nubari.groceries.data.repositories.UserRepositoryImpl
import com.nubari.groceries.domain.repositories.GroceryRepository
import com.nubari.groceries.domain.repositories.UserRepository
import com.nubari.groceries.domain.usecases.auth.AuthenticationUseCases
import com.nubari.groceries.domain.usecases.auth.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val baseUrl = "https://gorceriesonline.herokuapp.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideGroceriesApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: Api): UserRepository {
        return UserRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideAuthenticationUseCases(repository: UserRepository): AuthenticationUseCases {
        return AuthenticationUseCases(
            login = Login(userRepository = repository)
        )
    }

    @Provides
    @Singleton
    fun provideGroceriesDatabase(app: Application): GroceriesDatabase {
        return Room.databaseBuilder(
            app,
            GroceriesDatabase::class.java,
            GroceriesDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesGroceriesRepository(db: GroceriesDatabase): GroceryRepository {
        return GroceryRepositoryImpl(groceryDao = db.groceryDao)
    }
}