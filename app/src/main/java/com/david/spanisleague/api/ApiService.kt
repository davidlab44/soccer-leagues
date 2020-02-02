package com.david.spanisleague.api

import com.david.spanisleague.model.MovieResponse
import com.david.spanisleague.model.TeamResponse
import com.david.spanisleague.utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiService
 * provide accessor implementations themoviedb
 * retrofit service is instantiated by means of a companion object
 * themoviedb API key is passed as a parameter in the getCurrentDate method
 * returns a call with list MovieResponse class
 *
 * @author juan.rendon
 */

interface ApiService {
    /**
     * getMovieReviewListFromInternet
     * In this interface the token or api_key of themoviedb.com URL is passed as a parameter and returns a call of type MovieResponse
     */
    @GET("/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
    fun getMovieReviewListFromInternet(): Call<MovieResponse>

    @GET("/api/v1/json/1/eventsnext.php?id=134301")
    fun getTeamListFromInternet(): Call<TeamResponse>

    companion object {
        val instance: ApiService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}
