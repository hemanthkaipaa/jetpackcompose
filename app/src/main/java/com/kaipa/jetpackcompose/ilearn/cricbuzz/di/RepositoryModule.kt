package com.kaipa.jetpackcompose.ilearn.cricbuzz.di

import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.MatchRepositoryImpl
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMatchRepository(impl: MatchRepositoryImpl): MatchRepository
}