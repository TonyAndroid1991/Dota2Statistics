package com.example.dota2statistics.presentation.di

import com.example.dota2statistics.BuildConfig
import com.example.dota2statistics.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor { message -> println(message) }
                .setLevel(HttpLoggingInterceptor.Level.BODY))
        val client = builder.build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}