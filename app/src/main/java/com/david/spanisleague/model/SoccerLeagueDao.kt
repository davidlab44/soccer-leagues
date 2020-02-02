package com.david.spanisleague.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * SoccerLeagueDao
 *
 * This interface provides de DAO for room to Access Database @Entity(tableName = "soccer_league")
 *
 * @author david.mazo
 */
@Dao
interface SoccerLeagueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieReview(soccerLeague: SoccerLeague)

    @Query("SELECT * FROM soccer_league")
    fun getMovieReviewList(): List<SoccerLeague>

    @Query("SELECT * FROM soccer_league WHERE soccer_league.id=:id")
    fun getSoccerLeagueDetail(id: Int): SoccerLeague

    @Query("DELETE FROM soccer_league")
    fun deleteAllSoccerLeague()
}
