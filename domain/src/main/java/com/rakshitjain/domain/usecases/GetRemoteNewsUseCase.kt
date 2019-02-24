package com.rakshitjain.domain.usecases

import com.rakshitjain.domain.common.BaseJobUseCase
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

//It will get you the only the latest by fetching it from remote
class GetRemoteNewsUseCase(private val transformer: CoroutineContext,
                           private val repositories: NewsRepository): BaseJobUseCase<NewsSourcesEntity>(transformer){

    companion object {
        private const val PARAM_FILE_NEWS_ENTITY = "param:NewsStatus"
    }

    override suspend fun createJob(data: Map<String, Any>?): ReceiveChannel<NewsSourcesEntity> {
        return repositories.getNews()
    }

    suspend fun getNews(): ReceiveChannel<NewsSourcesEntity>{
        val data = HashMap<String, String>()
        return createJob(data)
    }


}