package com.example.medicare.data.services.di

import com.example.medicare.data.services.AccountService
import com.example.medicare.data.services.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
}