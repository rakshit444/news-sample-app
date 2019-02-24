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

    companion object {
        private val TAG = "viewmodel"
    }

    var mNews = MutableLiveData<Data<NewsSources>>()

    fun fetchNews() {
        coroutineScope.launch {
            val news = getNewsUseCase.getNews()
            news.consumeEach { response ->
                withContext(Dispatchers.Main) {
                    val mappedResponse = mapper.mapFrom(response)
                    mNews.value = mappedResponse
                }
            }
        }
    }

    fun getNewsLiveData() = mNews
}