package com.example.doctor.data.di

import com.example.doctor.data.childTable.ChildTable
import com.example.doctor.data.childTable.ChildTableImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ChildTableModule {
    @Binds
    abstract fun bindChildTable(impl: ChildTableImp): ChildTable
}