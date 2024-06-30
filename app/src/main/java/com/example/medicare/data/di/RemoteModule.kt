package com.example.medicare.data.di

import android.content.Context
import com.example.medicare.data.remoteApi.FcmApi
import com.example.medicare.core.notification.NotificationHandler
import com.example.medicare.core.notification.NotificationHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun bindRemote(): FcmApi {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(50, TimeUnit.SECONDS) // Adjust as needed
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://medicareapi-7b5f.onrender.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }
    @Provides
    fun provideNotificationHandler(
        @ApplicationContext context: Context,
    ): NotificationHandler = NotificationHandlerImpl(context)
}