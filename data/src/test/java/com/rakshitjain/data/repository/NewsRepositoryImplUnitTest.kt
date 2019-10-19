package com.rakshitjain.data.repository

import com.rakshitjain.data.TestUtil
import com.rakshitjain.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations



class NewsRepositoryImplUnitTest{

    @Mock
    private lateinit var remoteImpl: NewsRemoteImpl

    @Mock
    private lateinit var cache: NewsCacheImpl

    private lateinit var repo: NewsRepositoryImpl

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        repo = NewsRepositoryImpl(remoteImpl,cache)
    }

    @Test
    fun `two events with be emitted, One from remote and other from cache`(){
        setArticleListInDatabase(NewsSourcesEntity())
        setArticleListFromRemote(NewsSourcesEntity())

        val flowable = TestSubscriber<NewsSourcesEntity?>()
        repo.getNews().subscribeWith(flowable)

        flowable.awaitTerminalEvent()

        //Two events are fired for On Next Function
        assert(flowable.events[0].size == 2)
    }

    @Test
    fun `will first get empty event from Database then data from Remote`(){
        setArticleListInDatabase(NewsSourcesEntity())
        setArticleListFromRemote(TestUtil.createTestDataForNewsEntity(TestUtil.getDummyList()))

        val flowable = TestSubscriber<NewsSourcesEntity?>()
        repo.getNews().subscribeWith(flowable)

        flowable.awaitTerminalEvent()

        //Two events are fired for On Next Function
        val onNextEvents = flowable.events[0]
        assert(onNextEvents[0] is NewsSourcesEntity)
        //First will be from database
        assert((onNextEvents[0] as NewsSourcesEntity).articles.isEmpty())
        //Second will be from local
        assert(!(onNextEvents[1] as NewsSourcesEntity).articles.isEmpty())
    }

    @Test
    fun `will first get data from Database but error Remote`(){
        setArticleListInDatabase(TestUtil.createTestDataForNewsEntity(TestUtil.getDummyList()))
        errorFromRemote(Throwable("scsdc"))

        val flowable = TestSubscriber<NewsSourcesEntity?>()
        repo.getNews().subscribeWith(flowable)

        flowable.awaitTerminalEvent()

        //Two events are fired for On Next Function
        val onNextEvents = flowable.events[0]
        val onErrorEvents = flowable.events[1]
        assert(onNextEvents[0] is NewsSourcesEntity)
        //First will be from database
        assert(!(onNextEvents[0] as NewsSourcesEntity).articles.isEmpty())
        //Second will be error
        assert((onErrorEvents[0] as Throwable).message.equals("scsdc"))

    }

    private fun setArticleListInDatabase(entity: NewsSourcesEntity?) {
        `when`(cache.getNews()).thenReturn(Flowable.just(entity))
    }

    private fun setArticleListFromRemote(entity: NewsSourcesEntity?) {
        `when`(remoteImpl.getNews()).thenReturn(Flowable.just(entity))
    }

    private fun errorFromRemote(error: Throwable) {
        `when`(remoteImpl.getNews()).thenReturn(Flowable.error(error))
    }
}