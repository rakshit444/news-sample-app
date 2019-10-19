package com.rakshitjain.domain

import com.rakshitjain.domain.entities.NewsPublisherEntity
import com.rakshitjain.domain.entities.NewsSourcesEntity

object DomainUtils{


    fun createTestDataForNewsEntity(data: List<NewsPublisherEntity>): NewsSourcesEntity {
        return NewsSourcesEntity(status = "200",articles = data)
    }

    fun getDummyList():List<NewsPublisherEntity>{
        val data = NewsPublisherEntity(id = 12312,name = "sdcs",description = "sdcsc",url = "scds",category = "sdcs")
        return listOf(data)
    }

}