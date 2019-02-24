package com.rakshitjain.presentation.mappers

import com.rakshitjain.domain.common.Mapper
import com.rakshitjain.domain.entities.NewsPublisherEntity
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.presentation.entities.Data
import com.rakshitjain.presentation.entities.DataEntity
import com.rakshitjain.presentation.entities.Error
import com.rakshitjain.presentation.entities.NewsPublisher
import com.rakshitjain.presentation.entities.NewsSources

class NewsEntityMapper : Mapper<DataEntity<NewsSourcesEntity>, Data<NewsSources>>() {

    override fun mapFrom(data: DataEntity<NewsSourcesEntity>): Data<NewsSources> {
        when (data) {
            is DataEntity.SUCCESS -> return@mapFrom Data.SUCCESS(data.data?.let { mapSourcesToPresetation(it) })
            is DataEntity.ERROR -> return@mapFrom  Data.ERROR(error = Error( data.error.message))
            is DataEntity.LOADING -> return@mapFrom  Data.LOADING()
        }
    }

    private fun mapSourcesToPresetation(sources: NewsSourcesEntity)
            : NewsSources = NewsSources(status = sources?.status,
    articles = mapListArticlesToPresetation(sources?.articles))


    private fun mapListArticlesToPresetation(articles: List<NewsPublisherEntity>?)
            : List<NewsPublisher> = articles?.map { mapArticleToPresentation(it) }
            ?: emptyList()

    private fun mapArticleToPresentation(response: NewsPublisherEntity): NewsPublisher = NewsPublisher(
            id = response.id,
            name = response.name,
            description = response.description,
            url = response.url,
            category = response.category
    )

}