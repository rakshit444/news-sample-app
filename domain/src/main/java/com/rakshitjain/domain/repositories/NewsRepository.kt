package com.rakshitjain.domain.repositories

import com.rakshitjain.domain.entities.NewsSourcesEntity
import kotlinx.coroutines.channels.ReceiveChannel

interface NewsRepository {

    suspend fun getNews(): ReceiveChannel<NewsSourcesEntity>
    suspend fun getLocalNews(): ReceiveChannel<NewsSourcesEntity>
    suspend fun getRemoteNews(): ReceiveChannel<NewsSourcesEntity>

}