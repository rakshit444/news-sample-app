package com.rakshitjain.data.repository

import com.rakshitjain.data.api.RemoteNewsApi
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.data.entities.NewsDataEntityMapper
import com.rakshitjain.presentation.entities.DataEntity
import com.rakshitjain.presentation.entities.ErrorEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import java.lang.Exception

class NewsRemoteImpl constructor(private val api: RemoteNewsApi) : NewsDataStore {

    private val newsMapper = NewsDataEntityMapper()

    override suspend fun getNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        val producer = GlobalScope.produce<DataEntity<NewsSourcesEntity>> {
            try {
                val news = api.getNews().await()
                newsMapper.mapToEntity(news.articles).let { send(DataEntity.SUCCESS(it)) }
            } catch (e: Exception) {
                send(DataEntity.ERROR(ErrorEntity(e.message)))
            }
        }

        return producer
    }

}