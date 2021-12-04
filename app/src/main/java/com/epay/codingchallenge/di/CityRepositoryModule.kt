package com.epay.codingchallenge.di

import com.epay.codingchallenge.repository.CityRepository
import com.epay.codingchallenge.repository.CityRepositoryImpl
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
abstract class CityRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCityRepository(repository: CityRepositoryImpl): CityRepository
}