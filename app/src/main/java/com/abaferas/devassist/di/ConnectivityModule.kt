package com.abaferas.devassist.di

import android.content.Context
import com.abaferas.devassist.ui.utils.NetworkStateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {
    @Provides
    fun provideNetworkStateManager(@ApplicationContext context: Context): NetworkStateManager {
        return NetworkStateManager(context)
    }
}