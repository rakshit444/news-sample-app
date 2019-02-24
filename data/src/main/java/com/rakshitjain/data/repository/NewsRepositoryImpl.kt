package com.rakshitjain.data.repository

import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

class NewsRepositoryImpl(private val remote: NewsRemoteImpl,
                         private val cache: NewsCacheImpl) : NewsRepository {

    override suspend fun getLocalNews(): ReceiveChannel<NewsSourcesEntity> {
        return cache.getNews()
    }

    override suspend fun getRemoteNews(): ReceiveChannel<NewsSourcesEntity> {
        return remote.getNews()
    }

    override suspend fun getNews(): ReceiveChannel<NewsSourcesEntity> {
        val localNewsChannel = cache.getNews()
        GlobalScope.launch {
            val remoteNews = remote.getNews().receive()
            cache.saveArticles(remoteNews)
        }
        return localNewsChannel
    }
}