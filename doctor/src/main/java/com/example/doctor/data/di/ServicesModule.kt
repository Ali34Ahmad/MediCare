package com.example.doctor.data.di

import com.example.doctor.data.services.AccountService
import com.example.doctor.data.services.NotificationService
import com.example.doctor.data.services.StorageService
import com.example.doctor.data.services.impl.AccountServiceImpl
import com.example.doctor.data.services.impl.NotificationServiceImpl
import com.example.doctor.data.services.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
    @Binds
    abstract fun provideNotificationsService(impl: NotificationServiceImpl) : NotificationService
}