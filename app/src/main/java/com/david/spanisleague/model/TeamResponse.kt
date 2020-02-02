package com.david.spanisleague.model

/**
 * SoccerLeagueResponse
 *
 * class for the nested json that we get from the api call of themoviedb
 * it was created as an List type that in turn has another kind of json template called SoccerLeague
 *
 * @author juan.rendon
 */
class TeamResponse {
    var events: List<TeamReview> = listOf()
}
