package com.rakshitjain.data.repository

import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import com.rakshitjain.presentation.entities.DataEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.launch

class NewsRepositoryImpl(private val remote: NewsRemoteImpl,
                         private val cache: NewsCacheImpl) : NewsRepository {

    override suspend fun getLocalNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        return cache.getNews()
    }

    override suspend fun getRemoteNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        return remote.getNews()
    }

    override suspend fun getNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        val producerChannel = GlobalScope.produce() {

            launch {
                val localNewsChannel = cache.getNews()
                localNewsChannel.consumeEach { send(it) }
            }

            launch {
                val remoteNews = remote.getNews().receive()
                when (remoteNews) {
                    is DataEntity.SUCCESS -> {
                        cache.saveArticles(remoteNews)
                    }
                    is DataEntity.ERROR -> {
                        send(remoteNews)
                    }
                }
            }
        }

        return producerChannel
    }
}