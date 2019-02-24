package com.rakshitjain.presentation.news

import androidx.lifecycle.MutableLiveData
import com.rakshitjain.domain.common.Mapper
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.usecases.GetNewsUseCase
import com.rakshitjain.presentation.common.BaseViewModel
import com.rakshitjain.presentation.entities.Data
import com.rakshitjain.presentation.entities.Error
import com.rakshitjain.presentation.entities.NewsSources
import com.rakshitjain.presentation.entities.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import java.lang.Exception

class NewsViewModel(private val getNewsUseCase: GetNewsUseCase,
                    private val mapper: Mapper<NewsSourcesEntity, NewsSources>) : BaseViewModel() {

    companion object {
        private val TAG = "viewmodel"
    }
    private lateinit var jobs: Job

    var mNews = MutableLiveData<Data<NewsSources>>()

    fun fetchNews() {
        GlobalScope.launch(Dispatchers.Default) {
            try {
                val news = getNewsUseCase.getNews()
                news.consumeEach { response ->
                    withContext(Dispatchers.Main) {
                        val mappedResponse = mapper.mapFrom(response)
                        mNews.value = Data(responseType = Status.SUCCESSFUL, data = mappedResponse)
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main) {
                    mNews.value = Data(responseType = Status.ERROR,error = Error(message = e.message))
                }
            }
        }
    }

    fun getNewsLiveData() = mNews
}