package com.david.spanisleague.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * SoccerLeague
 *
 * data class for thesportsdb API, it gets all teams in a league
 *
 * @author david.mazo
 */
@Entity(tableName = "soccer_league")
data class SoccerLeague(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int,
        @SerializedName("idTeam")
        var idTeam: String?,
        @SerializedName("strTeam")
        var strTeam: String?,
        @SerializedName("strStadium")
        var strStadium: String?,
        @SerializedName("strTeamBadge")
        var strTeamBadge: String?,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String?,
        @SerializedName("intFormedYear")
        var intFormedYear: String?,
        @SerializedName("strTeamJersey")
        var strTeamJersey: String?,
        @SerializedName("strWebsite")
        var strWebsite: String?,
        @SerializedName("strFacebook")
        var strFacebook: String?,
        @SerializedName("strTwitter")
        var strTwitter: String?,
        @SerializedName("strInstagram")
        var strInstagram: String?
        /*
        var video: String?,
        var adult: String?,
        var title: String?,
        var popularity: String?,
        @SerializedName("overview")
        var summary: String?,
        @SerializedName("vote_count")
        var voteCount: Float,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("release_date")
        var releaseDate: String?,
        @SerializedName("vote_average")
        var voteAverage: String,
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        @SerializedName("original_title")
        var originalTitle: String?,
        @SerializedName("original_language")
        var originalLanguage: String?
        */
)
