package com.example.doctor.data.di

import com.example.doctor.data.repositories.AppointmentRepository
import com.example.doctor.data.repositories.ChildRepository
import com.example.doctor.data.repositories.ClinicRepository
import com.example.doctor.data.repositories.DoctorRepository
import com.example.doctor.data.repositories.UserRepository
import com.example.doctor.data.repositories.VaccineRepository
import com.example.doctor.data.repositories.impl.AppointmentRepositoryImpl
import com.example.doctor.data.repositories.impl.ChildRepositoryImpl
import com.example.doctor.data.repositories.impl.ClinicRepositoryImpl
import com.example.doctor.data.repositories.impl.DoctorRepositoryImpl
import com.example.doctor.data.repositories.impl.UserRepositoryImpl
import com.example.doctor.data.repositories.impl.VaccineRepositoryImpl
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