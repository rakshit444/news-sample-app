package com.rakshitjain.data.repository

import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.presentation.entities.DataEntity
import kotlinx.coroutines.channels.ReceiveChannel


interface NewsDataStore{
    suspend fun getNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>>
}