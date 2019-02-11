package com.rakshitjain.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news_articles")
data class NewsPublisherData(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @SerializedName("name") var name: String? = null,
        @SerializedName("description") var description: String? = null,
        @SerializedName("url") var url: String? = null,
        @SerializedName("category") var category: String? = null)