package com.rakshitjain.domain.usecases

import com.rakshitjain.domain.common.BaseJobUseCase
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import com.rakshitjain.presentation.entities.DataEntity
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

/**
 * It will first get articles from the local database and also update it with the latest
 * articles from remote
 */
class GetNewsUseCase(private val coroutineContext: CoroutineContext,
                     private val repositories: NewsRepository) : BaseJobUseCase<NewsSourcesEntity>(coroutineContext) {

    override suspend fun getDataChannel(data: Map<String, Any>?): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        return repositories.getNews()
    }

    override suspend fun sendToPresentation(data: DataEntity<NewsSourcesEntity>): DataEntity<NewsSourcesEntity> {
        return data
    }

    suspend fun getNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        val data = HashMap<String, String>()
        return getDataChannel(data)
    }
}