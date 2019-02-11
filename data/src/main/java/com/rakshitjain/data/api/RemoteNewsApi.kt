package com.rakshitjain.data.api

import com.rakshitjain.data.entities.NewsSourcesData
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteNewsApi {

    @GET("top-headlines?country=us&apiKey=" + "3d7231ede1094c40aef87891fd25bc73")
    fun getNews(): Flowable<NewsSourcesData>

}