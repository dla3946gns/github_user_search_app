package com.example.user_github_list.hilt

import com.example.user_github_list.remote.RetrofitInterface
import com.example.user_github_list.ui.repos.ReposRepository
import com.example.user_github_list.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    // dependency providers
    @Provides
    fun provideRetrofitInterface(): RetrofitInterface {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface::class.java)
    }

    @Provides
    fun provideRepository(retrofitInterface: RetrofitInterface): ReposRepository {
        return ReposRepository(retrofitInterface)
    }

}