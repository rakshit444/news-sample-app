package com.rakshitjain.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.rakshitjain.data.entities.NewsPublisherData

@Database(entities = arrayOf(NewsPublisherData::class), version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao
}