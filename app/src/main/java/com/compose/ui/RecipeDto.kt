package com.compose.ui

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "RecipeTable")
data class RecipeDto(
        @PrimaryKey
        @SerializedName("pk")
        var pk: Int,

        @SerializedName("title")
        @ColumnInfo(name = "title")
        var title: String,

        @SerializedName("publisher")
        @ColumnInfo(name = "publisher")
        var publisher: String,

        @SerializedName("featured_image")
        @ColumnInfo(name = "featured_image")
        var featuredImage: String,

        @ColumnInfo(name = "rating")
        @SerializedName("rating")
        var rating: Int = 0,

        @SerializedName("source_url")
        @ColumnInfo(name = "source_url")
        var sourceUrl: String,

//        @SerializedName("ingredients")
//        var ingredients: List<String> = emptyList(),

        @SerializedName("date_added")
        @ColumnInfo(name = "date_added")
        var dateAdded: String,

        @SerializedName("date_updated")
        @ColumnInfo(name = "date_updated")
        var dateUpdated: String,
)

