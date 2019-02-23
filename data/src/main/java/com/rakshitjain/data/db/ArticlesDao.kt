package com.rakshitjain.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rakshitjain.data.entities.NewsPublisherData
import io.reactivex.Flowable

@Dao
interface ArticlesDao{

    @Query("Select * from news_articles")
    fun getAllArticles(): Flowable<List<NewsPublisherData>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllArticles(articles: List<NewsPublisherData>)

    @Query("DELETE FROM news_articles")
    fun clear()

}