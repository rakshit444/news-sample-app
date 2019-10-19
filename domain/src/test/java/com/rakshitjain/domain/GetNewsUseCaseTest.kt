package com.rakshitjain.domain

import com.rakshitjain.domain.entities.NewsSourcesEntity
import com.rakshitjain.domain.repositories.NewsRepository
import com.rakshitjain.domain.usecases.GetNewsUseCase
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetNewsUseCaseTest {

    var transformer = AsyncFlowableTransformer<NewsSourcesEntity>()
    @Mock
    lateinit var repositories: NewsRepository

    lateinit var newsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        newsUseCase = GetNewsUseCase(transformer,repositories)
    }

    @Test
    fun `data from the repository`(){
        getArticlesListInResult(DomainUtils.createTestDataForNewsEntity(DomainUtils.getDummyList()))
        val subscriber = TestSubscriber<NewsSourcesEntity>()
        newsUseCase.getNews().subscribeWith(subscriber)

        subscriber.awaitTerminalEvent()
        assert((subscriber.events[0][0] as NewsSourcesEntity).articles.isNotEmpty())
    }

    @Test
    fun `data from the repository is null`(){
        getArticlesListInResult(NewsSourcesEntity())
        val subscriber = TestSubscriber<NewsSourcesEntity>()
        newsUseCase.getNews().subscribeWith(subscriber)

        subscriber.awaitTerminalEvent()
        assert((subscriber.events[0][0] as NewsSourcesEntity).articles.isEmpty())
    }

    private fun getArticlesListInResult(createTestDataForNewsEntity: NewsSourcesEntity) {
        `when`(repositories.getNews()).thenReturn(Flowable.just(createTestDataForNewsEntity))
    }
}