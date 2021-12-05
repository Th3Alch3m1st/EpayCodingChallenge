package com.epay.codingchallenge.di

import com.epay.codingchallenge.fakerepository.FakeCityRepositoryImpl
import com.epay.codingchallenge.repository.CityRepository
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
    replaces = [CityRepositoryModule::class]
)
abstract class MockCityRepository {
    @Singleton
    @Binds
    abstract fun provideCityRepository(impl: FakeCityRepositoryImpl): CityRepository
}