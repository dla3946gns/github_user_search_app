package com.example.user_github_list.hilt

import com.example.user_github_list.remote.RetrofitInterface
import com.example.user_github_list.ui.repos.ReposRepository
import com.example.user_github_list.ui.users.repository.UserDetailRepository
import com.example.user_github_list.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    // dependency providers
    @Provides
    fun provideRetrofitInterface(): RetrofitInterface {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(provideOkHttpClient(AppInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface::class.java)
    }

    @Provides
    fun provideRepository(retrofitInterface: RetrofitInterface): ReposRepository {
        return ReposRepository(retrofitInterface)
    }

    @Provides
    fun provideUserRepository(retrofitInterface: RetrofitInterface): UserDetailRepository {
        return UserDetailRepository(retrofitInterface)
    }

    private fun provideOkHttpClient(interceptor: AppInterceptor): OkHttpClient
            = OkHttpClient.Builder().run {
        addInterceptor(interceptor)
            .build()
    }

    class AppInterceptor: Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader(Constants.HEADER_ACCEPT_KEY, Constants.HEADER_ACCEPT_VALUE)
                .addHeader(Constants.HEADER_AUTHORIZATION_KEY, Constants.HEADER_AUTHORIZATION_VALUE)
                .addHeader(Constants.HEADER_API_VERSION_KEY, Constants.HEADER_API_VERSION_VALUE)
                .build()
            proceed(newRequest)
        }
    }

}