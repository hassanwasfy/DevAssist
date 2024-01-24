package com.abaferas.devassist.di

import com.abaferas.devassist.data.repository.IRepository
import com.abaferas.devassist.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideRepository(iRepositoryImpl: RepositoryImpl): IRepository

}