package com.example.medicare.data.di

import com.example.medicare.data.childTable.ChildTable
import com.example.medicare.data.childTable.ChildTableImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ChildTableModule {
    @Binds
    abstract fun bindChildTable(impl: ChildTableImp ): ChildTable
}