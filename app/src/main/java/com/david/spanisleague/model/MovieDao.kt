package com.david.spanisleague.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * SoccerLeagueDao
 *
 * @author david.mazo
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieReview(movieReview: MovieReview)

    @Query("SELECT * FROM movie_review")
    fun getMovieReviewList(): List<MovieReview>

    @Query("SELECT * FROM movie_review WHERE movie_review.id=:id")
    fun getMovieReviewDetail(id: Int): MovieReview

    @Query("DELETE FROM movie_review")
    fun deleteAllSoccerLeague()
}
