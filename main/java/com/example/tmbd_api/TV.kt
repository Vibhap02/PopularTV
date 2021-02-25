package com.example.tmbd_api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName="tv")
data class TV(
        @PrimaryKey
    val id:Long,

        val name:String,

        @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath:String,

        @ColumnInfo(name="backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath:String,

        @ColumnInfo(name="first_air_date")
    @SerializedName("first_air_date")
    val firstAirDate: Date,

        @ColumnInfo(name="overview")
    @SerializedName("overview")
    val overView:String
)

