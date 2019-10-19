package com.rakshitjain.presentation.news

import com.rakshitjain.data.entities.NewsPublisherData
import com.rakshitjain.domain.entities.NewsPublisherEntity
import com.rakshitjain.domain.entities.NewsSourcesEntity

object PresentationUtil{

    fun createTestDataForNews():List<NewsPublisherData>{
        val data = NewsPublisherData(id = 12312,name = "sdcs",description = "sdcsc",url = "scds",category = "sdcs")
        return listOf<NewsPublisherData>(data)
    }

    fun createTestDataForNewsEntity(data: List<NewsPublisherEntity>): NewsSourcesEntity{
        return NewsSourcesEntity(status = "200",articles = data)
    }

    fun getDummyList():List<NewsPublisherEntity>{
        val data = NewsPublisherEntity(id = 12312,name = "sdcs",description = "sdcsc",url = "scds",category = "sdcs")
        return listOf(data)
    }

    fun getDummyListData():List<NewsPublisherData>{
        val data = NewsPublisherData(id = 12312,name = "sdcs",description = "sdcsc",url = "scds",category = "sdcs")
        return listOf(data)
    }
}