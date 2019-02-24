package com.rakshitjain.domain.usecases

import com.rakshitjain.domain.common.BaseJobUseCase
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import com.rakshitjain.presentation.entities.DataEntity
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

// It will just return the list of articles in local database
class GetLocalNewsUseCase(private val transformer: CoroutineContext,
                          private val repositories: NewsRepository): BaseJobUseCase<NewsSourcesEntity>(transformer){

    companion object {
        private const val PARAM_FILE_NEWS_ENTITY = "param:NewsStatus"
    }

    override suspend fun createJob(data: Map<String, Any>?): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        return repositories.getNews()
    }

    suspend fun getNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>>{
        val data = HashMap<String, String>()
        return createJob(data)
    }


}