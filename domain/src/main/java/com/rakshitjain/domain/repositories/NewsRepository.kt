package com.rakshitjain.domain.repositories

import com.rakshitjain.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

interface NewsRepository {

    fun getNews(): Flowable<NewsSourcesEntity>
    fun getLocalNews(): Flowable<NewsSourcesEntity>
    fun getRemoteNews(): Flowable<NewsSourcesEntity>

}