package com.abaferas.devassist.di

import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.abaferas.devassist.BuildConfig
import com.abaferas.devassist.Constants

@Module
@InstallIn(SingletonComponent::class)
object AiModule {
    @Singleton
    @Provides
    fun provideGenerativeModel(): GenerativeModel {
        return GenerativeModel(
            modelName = Constants.MODEL_NAME,
            apiKey = BuildConfig.AI_API_KEY
        )
    }
}