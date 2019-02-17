package com.rakshitjain.data.repository

import com.rakshitjain.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable
import io.reactivex.Single


interface NewsDataStore{
    fun getNews(): Flowable<NewsSourcesEntity>
}