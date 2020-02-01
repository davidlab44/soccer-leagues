package com.david.spanisleague.rest

import com.david.spanisleague.data.User
import com.david.spanisleague.utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * @author david.mazo
 */

interface ApiService {

    @GET("/users")
    fun getMovieReviewListFromInternet(): Call<List<User>>

    companion object {
        val instance: ApiService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}
