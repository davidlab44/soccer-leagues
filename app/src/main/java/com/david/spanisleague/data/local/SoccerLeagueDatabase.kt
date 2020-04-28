package com.david.spanisleague.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * SoccerLeagueDatabase
 *
 * We will use this class to create other classes
 * This class is abstract because is implemented by Room
 *
 * @author david.mazo
 */

@Database(entities = [SoccerLeague::class, TeamEvent::class], version = 14, exportSchema = false)
abstract class SoccerLeagueDatabase : RoomDatabase() {

    abstract fun getSoccerLeagueDAO(): SoccerLeagueDao
    abstract fun getTeamEventDAO(): TeamDao

    companion object {
        fun getSoccerLeague(context: Context) = Room.databaseBuilder(context.applicationContext,
                SoccerLeagueDatabase::class.java, "SoccerLeagueDatabase")
                .allowMainThreadQueries()
                .build()
    }
}
