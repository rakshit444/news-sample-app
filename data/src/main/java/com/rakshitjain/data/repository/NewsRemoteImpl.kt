package com.rakshitjain.data.repository

import com.rakshitjain.data.api.RemoteNewsApi
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.data.entities.NewsDataEntityMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsRemoteImpl constructor(private val api: RemoteNewsApi) : NewsDataStore {

    private val newsMapper = NewsDataEntityMapper()

    override suspend fun getNews(): ReceiveChannel<NewsSourcesEntity> {
        val channel = Channel<NewsSourcesEntity>()
        try {
            GlobalScope.launch {
                async {
                    val news = api.getNews().await()
                    newsMapper.mapToEntity(news)?.let { channel.send(it) }
                }.start()
            }
        }catch (e:Exception){

        }
        return channel
    }

}