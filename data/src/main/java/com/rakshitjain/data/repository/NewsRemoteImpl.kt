package com.rakshitjain.data.repository

import com.rakshitjain.data.api.RemoteNewsApi
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.data.entities.NewsDataEntityMapper
import io.reactivex.Flowable

class NewsRemoteImpl constructor(private val api:RemoteNewsApi): NewsDataStore {

    private val newsMapper =  NewsDataEntityMapper()

    override fun getNews(): Flowable<NewsSourcesEntity> {

        return api.getNews().map { newsMapper.mapToEntity(it) }
    }

}