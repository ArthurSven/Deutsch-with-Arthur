package com.devapps.deutschmitarthur.di

import android.app.Application
import com.devapps.deutschmitarthur.Application.DeutschMitArthur
import com.devapps.deutschmitarthur.data.remote.TriviaApi
import com.devapps.deutschmitarthur.ui.MainActivity
import com.devapps.deutschmitarthur.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTriviaApi(retrofit: Retrofit): TriviaApi {
        return retrofit.create(TriviaApi::class.java)
    }
}