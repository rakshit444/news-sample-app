package com.rakshitjain.data.repository

import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

open class NewsRepositoryImpl(private val remote: NewsRemoteImpl,
                         private val cache: NewsCacheImpl) : NewsRepository {

    override fun getLocalNews(): Flowable<NewsSourcesEntity> {
        return cache.getNews()
    }

    override fun getRemoteNews(): Flowable<NewsSourcesEntity> {
        return remote.getNews()
    }

    override fun getNews(): Flowable<NewsSourcesEntity> {
        val updateNewsFlowable = remote.getNews()
        return Flowable.concat(cache.getNews(),updateNewsFlowable)

    }

}