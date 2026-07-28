package com.kaipa.jetpackcompose.ilearn.cricbuzz.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface MatchDAO {

    @Query("SELECT * FROM matches WHERE type = :matchType")
    fun observeByType(matchType:String): Flow<List<MatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(matches: List<MatchEntity>)

    @Query("DELETE FROM matches WHERE type = :matchType")
    suspend fun deleteByType(matchType: String)
}