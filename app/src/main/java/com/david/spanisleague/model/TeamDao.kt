package com.david.spanisleague.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * MovieDao
 *
 * to Access Database @Entity(tableName = "movie_review") data class MovieReview
 * facilitates access to stored data with the following methods
 *
 * @author david.mazo
 */
@Dao
interface TeamDao {
    /**
     * insertMovieReview -> insert a registry of movie_review into Database movie_review
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeamReview(teamReview: TeamReview)

    /**
     * getMovieReviewList -> consult a list of all the movie_review
     */
    @Query("SELECT * FROM team_review")
    fun getTeamReviewList(): List<TeamReview>

    /**
     * getMovieReviewDetail -> consult a movie_review
     */
    @Query("SELECT * FROM team_review WHERE team_review.id=:id")
    fun getTeamReviewDetail(id: Int): TeamReview

    @Query("DELETE FROM team_review")
    fun deleteAllTeamEvents()
}
