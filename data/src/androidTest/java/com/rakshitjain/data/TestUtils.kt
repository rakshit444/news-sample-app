package com.rakshitjain.data

import com.rakshitjain.data.entities.NewsPublisherData

object TestUtils{

    fun createTestDataForNews():List<NewsPublisherData>{
        val data = NewsPublisherData(id = 12312,name = "sdcs",description = "sdcsc",url = "scds",category = "sdcs")
        return listOf<NewsPublisherData>(data)
    }
}