package com.example.doctor.data.di

import com.example.doctor.data.storage.ImageUploader
import com.example.doctor.data.storage.impl.ImageUploaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun provideImageUploader(impl: ImageUploaderImpl) : ImageUploader
    
}