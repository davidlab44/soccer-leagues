package com.david.spanisleague.ui

import com.david.spanisleague.model.SoccerLeague

/**
 * SoccerLeagueEvents
 *
 * The onItemClicked method is created to be implemented in the classes that use the interface
 *
 * @author juan.rendon
 */
interface SoccerLeagueEvents {
    fun onItemClicked(soccerLeague: SoccerLeague)
}
