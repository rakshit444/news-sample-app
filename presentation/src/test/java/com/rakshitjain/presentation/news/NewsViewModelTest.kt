package com.rakshitjain.presentation.news

import com.rakshitjain.domain.common.Mapper
import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import com.rakshitjain.domain.usecases.GetNewsUseCase
import com.rakshitjain.presentation.entities.NewsSources
import com.rakshitjain.presentation.mappers.NewsEntityMapper
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NewsViewModelTest {

    var transformer = AsyncFlowableTransformer<NewsSourcesEntity>()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var viewModel: NewsViewModel

    @Mock
    lateinit var repositories: NewsRepository

    lateinit var newsUseCase: GetNewsUseCase

    lateinit var mapper: Mapper<NewsSourcesEntity, NewsSources>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mapper = NewsEntityMapper()
        newsUseCase = GetNewsUseCase(transformer, repositories)
        viewModel = NewsViewModel(newsUseCase, mapper)
    }

    @Test
    fun fetchNewsForEmptyDataFromUseCase() {
        setDataForRepository(NewsSourcesEntity())

        viewModel.fetchNews()

        val viewModelValue = viewModel.getNewsLiveData().value
        assert(viewModelValue?.data == null)
    }

    @Test
    fun fetchNewsForDataFromUseCase() {
        setDataForRepository(PresentationUtil.createTestDataForNewsEntity(LIST))

        viewModel.fetchNews()

        val viewModelValue = viewModel.getNewsLiveData().value

        assert(viewModelValue?.data?.articles?.size == LIST.size)
    }

    private fun setDataForRepository(entity: NewsSourcesEntity?) {
        `when`(repositories.getNews()).thenReturn(Flowable.just(entity))
    }

    companion object {
        val LIST =PresentationUtil.getDummyList()
    }
}