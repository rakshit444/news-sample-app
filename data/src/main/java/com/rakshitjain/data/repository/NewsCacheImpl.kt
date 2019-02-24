package com.rakshitjain.data.repository

import com.rakshitjain.data.db.ArticlesDao
import com.rakshitjain.data.db.NewsDatabase
import com.rakshitjain.data.entities.NewsDataEntityMapper
import com.rakshitjain.data.entities.NewsEntityDataMapper
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.presentation.entities.DataEntity
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.reactive.openSubscription

class NewsCacheImpl(private val database: NewsDatabase,
                    private val entityToDataMapper: NewsEntityDataMapper,
                    private val dataToEntityMapper: NewsDataEntityMapper) : NewsDataStore {

    private val dao: ArticlesDao = database.getArticlesDao()

    override suspend fun getNews(): ReceiveChannel<DataEntity<NewsSourcesEntity>> {
        val mappedValue = dao.getAllArticles().map { it ->
            DataEntity.SUCCESS(dataToEntityMapper.mapToEntity(it))
        }
        return mappedValue.openSubscription()
    }

    fun saveArticles(response: DataEntity<NewsSourcesEntity>) {
        entityToDataMapper.mapResponseToData(response)?.let {
            dao.clear()
            dao.saveAllArticles(it)
        }
    }

}