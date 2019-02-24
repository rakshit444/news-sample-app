package com.rakshitjain.domain.repositories

import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.presentation.entities.DataEntity
import kotlinx.coroutines.channels.ReceiveChannel

interface NewsRepository {

    suspend fun getNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>>
    suspend fun getLocalNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>>
    suspend fun getRemoteNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>>

}