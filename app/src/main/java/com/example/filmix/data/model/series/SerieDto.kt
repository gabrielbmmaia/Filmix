package com.example.filmix.data.model.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmix.core.Constants.SERIE_TABLE
import com.example.filmix.domain.model.serie.Serie
import com.google.gson.annotations.SerializedName

@Entity(tableName = SERIE_TABLE)
data class SerieDto(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @SerializedName("name")
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Int?,
    @SerializedName("vote_count")
    val voteCount: Int?
) {
    fun toSerie() =
        Serie(
            id = id,
            title = title,
            posterPath = posterPath,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
}