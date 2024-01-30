package com.abaferas.devassist.di

import com.abaferas.devassist.data.repository.AuthRepository
import com.abaferas.devassist.data.repository.AuthRepositoryImpl
import com.abaferas.devassist.data.repository.LearningItemsRepository
import com.abaferas.devassist.data.repository.LearningItemsRepositoryImpl
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
    abstract fun provideAuthRepository(iAuthRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun provideItemsRepository(learningItemsRepositoryImpl: LearningItemsRepositoryImpl): LearningItemsRepository


}