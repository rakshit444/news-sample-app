package com.rakshitjain.data.entities

import com.google.gson.annotations.SerializedName
import com.rakshitjain.domain.entities.NewsPublisherEntity
import com.rakshitjain.domain.entities.NewsSourcesEntity

data class NewsSourcesData(
        @SerializedName("status") var status: String? = null,
        @SerializedName("articles") var articles: List<NewsPublisherData> = emptyList()
)

class NewsDataEntityMapper constructor() {

    fun mapToEntity(data: NewsSourcesData?): NewsSourcesEntity? = NewsSourcesEntity(
            status = data?.status,
            articles = mapListArticlesToEntity(data?.articles)
    )

    fun mapToEntity(articles: List<NewsPublisherData>?): NewsSourcesEntity? = NewsSourcesEntity(
            articles = mapListArticlesToEntity(articles)
    )

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

    fun mapToEntity(data: NewsSourcesEntity?): NewsSourcesData? = NewsSourcesData(
            status = data?.status,
            articles = mapListArticlesToEntity(data?.articles)
    )

    fun mapListArticlesToEntity(articles: List<NewsPublisherEntity>?)
            : List<NewsPublisherData> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: NewsPublisherEntity): NewsPublisherData = NewsPublisherData(
            id = response.id,
            name = response.name,
            description = response.description,
            url = response.url,
            category = response.category
    )


}

