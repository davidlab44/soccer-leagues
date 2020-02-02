package com.david.spanisleague.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * SoccerLeagueDao
 *
 * to Access Database @Entity(tableName = "team_review")
 * facilitates access to stored data with its methods
 *
 * @author david.mazo
 */
@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeamReview(teamEvent: TeamEvent)

    @Query("SELECT * FROM team_review")
    fun getTeamReviewList(): List<TeamEvent>

    @Query("SELECT * FROM team_review WHERE team_review.id=:id")
    fun getTeamReviewDetail(id: Int): TeamEvent

    @Query("DELETE FROM team_review")
    fun deleteAllTeamEvents()
}
