package com.codarchy.data.di

import android.content.Context
import com.codarchy.data.network.ChocolateFactoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Singleton
    @Provides
    fun provideChocolateFactoryApi(okHttpClient: OkHttpClient): ChocolateFactoryApi = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ChocolateFactoryApi::class.java)

}
