package com.david.spanisleague.repository

import android.content.Context
import android.util.Log
import com.david.spanisleague.R
import com.david.spanisleague.api.ApiService
import com.david.spanisleague.model.MovieDao
import com.david.spanisleague.model.MovieResponse
import com.david.spanisleague.model.MovieReview
import com.david.spanisleague.model.SoccerLeagueDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Soccer Leagues Repository
 *
 * This Class returns the data to the MainActivity
 * a requestMovieReviewList method is created and asks if there is access to the internet performs a callback.enqueue,
 * through retrofit and populates the bd implemented with room
 *
 * @author david.mazo
 */
class MovieRepository(private val context: Context) {

    private val apiService = ApiService.instance
    private val movieDatabase: MovieDao get() = SoccerLeagueDatabase.getMovieDatabase(context).getMovieDAO()

    /*
    fun requestMovieReviewList(league:String): List<MovieReview> {
        apiService.getMovieReviewListFromInternet().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(callMovieResponse: Call<MovieResponse>, response: Response<MovieResponse>) {
                when (response.code()) {
                    200 -> insertMovieReviewListIntoDatabase(response)
                    else -> Log.e(context.getString(R.string.error_tag), context.getString(R.string.error_response_code_different_to_200))
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(context.getString(R.string.error_tag), t.printStackTrace().toString())
            }
        })
        return getMovieReviewList()
    }
    */

    fun requestMovieReviewList(league:String): List<MovieReview> {
        apiService.getMovieReviewListFromInternet(league).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(callMovieResponse: Call<MovieResponse>, response: Response<MovieResponse>) {
                when (response.code()) {
                    200 -> {
                        deleteMovieReviewList()
                        insertMovieReviewListIntoDatabase(response)
                    }
                    else -> Log.e(context.getString(R.string.error_tag), context.getString(R.string.error_response_code_different_to_200))
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(context.getString(R.string.error_tag), t.printStackTrace().toString())
            }
        })
        return getMovieReviewList()
    }

    private fun insertMovieReviewListIntoDatabase(response: Response<MovieResponse>) {
        if (response.body() != null) {
            for (movieReview: MovieReview in response.body()!!.teams) {
                movieDatabase.insertMovieReview(movieReview)
            }
        }
    }

    fun getMovieReviewList(): List<MovieReview> {
        return SoccerLeagueDatabase.getMovieDatabase(context).getMovieDAO().getMovieReviewList()
    }


    fun deleteMovieReviewList() {
        return SoccerLeagueDatabase.getMovieDatabase(context).getMovieDAO().deleteAllSoccerLeague()
    }
}
