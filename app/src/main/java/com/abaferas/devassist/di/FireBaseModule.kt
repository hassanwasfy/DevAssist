package com.abaferas.devassist.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FireBaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }


}