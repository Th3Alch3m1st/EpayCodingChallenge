package com.epay.codingchallenge.network.di

import com.epay.codingchallenge.network.repository.WeatherInfoRepository
import com.epay.codingchallenge.network.repository.WeatherInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Rafiqul Hasan
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideManufacturerRepository(api: WeatherInfoRepositoryImpl): WeatherInfoRepository
}