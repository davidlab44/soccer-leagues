package com.david.spanisleague.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * TeamEvent
 *
 * data class for thesportsdb API, it gets next five events for a team
 *
 * @author david.mazo
 */
@Entity(tableName = "team_review")
data class TeamEvent(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int,
        @SerializedName("strEvent")
        var strEvent: String?
) {
    override fun toString(): String {
        return strEvent.toString()
    }
}
