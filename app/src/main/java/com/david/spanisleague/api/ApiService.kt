package com.david.spanisleague.api

import com.david.spanisleague.model.SoccerLeagueResponse
import com.david.spanisleague.model.TeamResponse
import com.david.spanisleague.utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiService
 *
 * This Interface calls an API with list of SoccerLeagues
 *
 * @author david.mazo
 */

interface ApiService {

    @GET("api/v1/json/1/search_all_teams.php?")
    fun getMovieReviewListFromInternet(@Query("l") l: String): Call<SoccerLeagueResponse>

    @GET("/api/v1/json/1/eventsnext.php?id=134301")
    fun getTeamListFromInternet(): Call<TeamResponse>

    companion object {
        val instance: ApiService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}
