package com.rakshitjain.domain.repositories

import com.rakshitjain.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

interface NewsRepository {

    fun getNews(): Flowable<NewsSourcesEntity>

}