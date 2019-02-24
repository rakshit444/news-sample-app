package com.rakshitjain.data.api

import com.rakshitjain.data.entities.NewsSourcesData
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RemoteNewsApi {

    @GET("top-headlines?country=us")
    fun getNews(): Deferred<NewsSourcesData>

}