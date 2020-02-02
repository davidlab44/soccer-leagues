package com.david.spanisleague.model

/**
 * MovieResponse
 *
 * class for the nested json that we get from the api call of themoviedb
 * it was created as an List type that in turn has another kind of json template called SoccerLeague
 *
 * @author juan.rendon
 */
class MovieResponse {
    var teams: List<SoccerLeague> = listOf()
}
