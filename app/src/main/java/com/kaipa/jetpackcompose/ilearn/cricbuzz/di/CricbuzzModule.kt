package com.kaipa.jetpackcompose.ilearn.cricbuzz.di

import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.repository.MatchRepositoryImpl
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.repository.MatchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CricbuzzModule {
    @Binds
    @Singleton
    abstract fun bindMatchRepository(impl : MatchRepositoryImpl): MatchRepository
}

/**
 * @module says hilt that this class contains hilt build dependencies to look for.
 * @InstallIn(SingletonComponent::class) - the module lifecycle where it lives throughout app till it dies
 * @Binds hilt look for binds and check for params to bind the interface w.r.t impl class
 * @Singleton is for build once and reuse - without this it will create a new instance everytime
 * Rule
 * The module is always defined as abstract class and binding functions must be abstract
 */