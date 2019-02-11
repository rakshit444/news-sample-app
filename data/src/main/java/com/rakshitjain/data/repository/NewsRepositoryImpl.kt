package com.rakshitjain.data.repository

import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class NewsRepositoryImpl(private val remote: NewsRemoteImpl,
                         private val cache: NewsCacheImpl) : NewsRepository {

    override fun getNews(): Flowable<NewsSourcesEntity> {
        remote.getNews().observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
                .subscribe ( {remoteNews -> cache.saveArticles(remoteNews)},{} )
        return cache.getNews()
    }
}