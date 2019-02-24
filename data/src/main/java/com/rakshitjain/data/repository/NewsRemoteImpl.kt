package com.rakshitjain.data.repository

import com.rakshitjain.data.api.RemoteNewsApi
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.data.entities.NewsDataEntityMapper
import com.rakshitjain.presentation.entities.DataEntity
import com.rakshitjain.presentation.entities.ErrorEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import java.lang.Exception

class NewsRemoteImpl constructor(private val api: RemoteNewsApi) : NewsDataStore {

    private val newsMapper = NewsDataEntityMapper()

    override suspend fun getNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        val channel = Channel<DataEntity<NewsSourcesEntity>>()
        GlobalScope.async {
            try {
                val news = api.getNews().await()
                newsMapper.mapToEntity(news.articles).let { channel.send(DataEntity.SUCCESS(it)) }
            } catch (e: Exception) {
                channel.send(DataEntity.ERROR(ErrorEntity(e.message)))
            }
        }.start()

        return channel
    }

}