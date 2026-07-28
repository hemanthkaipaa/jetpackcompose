package com.kaipa.jetpackcompose.ilearn.cricbuzz.data

import com.kaipa.cricbuzz.data.mapper.toDomain
import com.kaipa.cricbuzz.data.mapper.toEntity
import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.room.MatchDAO
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.Match
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchRepository
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(val dao: MatchDAO, val fakeDataSource: FakeMatchDataSource) : MatchRepository {

    override fun getMatches(matchType: MatchType): Flow<List<Match>> =
        dao.observeByType(matchType.name)
            .map { entities -> entities.map { it.toDomain() } }
            .onStart { refresh(matchType) }



    private suspend fun refresh(type: MatchType) {

        runCatching {
            val dtos = fakeDataSource.fetchMatchesList(type.name)
            dao.upsert(dtos.map{it.toEntity(type)})
        }

    }
}