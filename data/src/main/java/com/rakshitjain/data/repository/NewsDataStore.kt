package com.rakshitjain.data.repository

import com.rakshitjain.domain.entities.NewsSourcesEntity
import kotlinx.coroutines.channels.ReceiveChannel


interface NewsDataStore{
    suspend fun getNews(): ReceiveChannel<NewsSourcesEntity>
}