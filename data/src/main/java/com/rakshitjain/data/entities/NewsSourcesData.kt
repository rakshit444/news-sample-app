package com.rakshitjain.data.entities

import com.google.gson.annotations.SerializedName
import com.rakshitjain.domain.entities.NewsPublisherEntity
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.presentation.entities.DataEntity

data class NewsSourcesData(
        @SerializedName("status") var status: String? = null,
        @SerializedName("articles") var articles: List<NewsPublisherData> = emptyList()
)

class NewsDataEntityMapper constructor() {

    fun mapToEntity(mapArticles: List<NewsPublisherData>?) =
            NewsSourcesEntity(articles = mapListArticlesToEntity(mapArticles))


    fun mapListArticlesToEntity(articles: List<NewsPublisherData>?)
            : List<NewsPublisherEntity> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: NewsPublisherData): NewsPublisherEntity = NewsPublisherEntity(
            id = response.id,
            name = response.name,
            description = response.description,
            url = response.url,
            category = response.category
    )


}


class NewsEntityDataMapper constructor() {

    fun mapArticleToData(response: NewsPublisherEntity): NewsPublisherData = NewsPublisherData(
            id = response.id,
            name = response.name,
            description = response.description,
            url = response.url,
            category = response.category
    )

    fun mapResponseToData(response: DataEntity<NewsSourcesEntity>): List<NewsPublisherData>? {
        when (response) {
            is DataEntity.SUCCESS<NewsSourcesEntity> ->
                return@mapResponseToData response.data?.articles?.map { mapArticleToData(it) }
            is DataEntity.ERROR<NewsSourcesEntity> ->
                return@mapResponseToData response.data?.articles?.map { mapArticleToData(it) }
            is DataEntity.LOADING<NewsSourcesEntity> ->
                return@mapResponseToData response.data?.articles?.map { mapArticleToData(it) }
        }
    }


}

