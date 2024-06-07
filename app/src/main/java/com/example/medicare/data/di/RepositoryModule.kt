package com.example.medicare.data.di

import com.example.medicare.data.repositories.AppointmentRepository
import com.example.medicare.data.repositories.ChildRepository
import com.example.medicare.data.repositories.ClinicRepository
import com.example.medicare.data.repositories.DoctorRepository
import com.example.medicare.data.repositories.UserRepository
import com.example.medicare.data.repositories.VaccineRepository
import com.example.medicare.data.repositories.impl.AppointmentRepositoryImpl
import com.example.medicare.data.repositories.impl.ChildRepositoryImpl
import com.example.medicare.data.repositories.impl.ClinicRepositoryImpl
import com.example.medicare.data.repositories.impl.DoctorRepositoryImpl
import com.example.medicare.data.repositories.impl.UserRepositoryImpl
import com.example.medicare.data.repositories.impl.VaccineRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideChildRepository(repo: ChildRepositoryImpl): ChildRepository

    @Binds
    @Singleton
    abstract fun provideUserRepository(repo: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun provideClinicRepository(repo: ClinicRepositoryImpl): ClinicRepository

    @Binds
    @Singleton
    abstract fun provideDoctorRepository(repo: DoctorRepositoryImpl): DoctorRepository

    @Binds
    @Singleton
    abstract fun provideAppointmentRepository(repo: AppointmentRepositoryImpl): AppointmentRepository

    @Binds
    @Singleton
    abstract fun provideVaccineRepository(repo: VaccineRepositoryImpl): VaccineRepository

}