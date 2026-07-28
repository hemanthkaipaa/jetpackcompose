package com.kaipa.jetpackcompose.ilearn.cricbuzz.di

import android.content.Context
import androidx.room.Room
import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.room.AppDB
import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.room.MatchDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDB{
        return Room.databaseBuilder(context, AppDB::class.java,"cricbuzz.db").build()
    }

    @Provides
    fun matchDao(db: AppDB): MatchDAO = db.matchDao()

}