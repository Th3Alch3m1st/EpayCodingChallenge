package com.epay.codingchallenge.di

import com.epay.codingchallenge.network.di.RepositoryModule
import com.epay.codingchallenge.network.repository.WeatherInfoRepository
import com.epay.codingchallenge.fakerepository.FakeWeatherInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Created By Rafiqul Hasan
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class MockRepository {
    @Singleton
    @Binds
    abstract fun provideRepository(impl: FakeWeatherInfoRepositoryImpl): WeatherInfoRepository
}