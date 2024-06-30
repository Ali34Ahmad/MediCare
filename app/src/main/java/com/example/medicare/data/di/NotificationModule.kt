package com.example.medicare.data.di

import android.app.NotificationManager
import android.content.Context
import com.example.medicare.core.notification.NotificationHandler
import com.example.medicare.core.notification.NotificationHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Provides
    @Singleton
    @ApplicationContext
    fun provideNotificationHandler(
        context: Context
    ): NotificationHandler =
         NotificationHandlerImpl(context)
}
