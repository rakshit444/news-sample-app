package com.rakshitjain.presentation.news

import androidx.lifecycle.MutableLiveData
import com.rakshitjain.domain.common.Mapper
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.usecases.GetNewsUseCase
import com.rakshitjain.presentation.common.BaseViewModel
import com.rakshitjain.presentation.entities.Data
import com.rakshitjain.presentation.entities.DataEntity
import com.rakshitjain.presentation.entities.NewsSources
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach

class NewsViewModel(private val getNewsUseCase: GetNewsUseCase,
                    private val mapper: Mapper<DataEntity<NewsSourcesEntity>, Data<NewsSources>>) : BaseViewModel() {


    var mNews = MutableLiveData<Data<NewsSources>>()

    fun fetchNews() {
        launch {
            val news = getNewsUseCase.getNews()
            news.consumeEach { response ->
                val mappedResponse = mapper.mapFrom(response)

                //Switching the context to main
                withContext(Dispatchers.Main) {
                    mNews.postValue(mappedResponse)
                }
            }
        }
    }

    fun getNewsLiveData() = mNews
}