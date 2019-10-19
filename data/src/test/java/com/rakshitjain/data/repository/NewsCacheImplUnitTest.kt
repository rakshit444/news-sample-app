package com.rakshitjain.data.repository

import com.rakshitjain.data.TestUtil
import com.rakshitjain.data.db.ArticlesDao
import com.rakshitjain.data.db.NewsDatabase
import com.rakshitjain.data.entities.NewsDataEntityMapper
import com.rakshitjain.data.entities.NewsEntityDataMapper
import com.rakshitjain.data.entities.NewsPublisherData
import com.rakshitjain.domain.entities.NewsPublisherEntity
import com.rakshitjain.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class NewsCacheImplUnitTest {

    private var entityToDataMapper = NewsEntityDataMapper()
    private var dataToEntityMapper = NewsDataEntityMapper()
    private lateinit var local: NewsCacheImpl

    @Mock
    private lateinit var database: NewsDatabase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val dao = mock(ArticlesDao::class.java)
        `when`(database.getArticlesDao()).thenReturn(dao)

        local = NewsCacheImpl(database, entityToDataMapper, dataToEntityMapper)
    }

    @Test
    fun getListFromDatabase() {
        setArticleListInDatabase(LIST_DATA)

        val flowable = TestSubscriber<NewsSourcesEntity>()
        local.getNews().subscribe(flowable)

        flowable.assertValue(NewsSourcesEntity(articles = LIST_ENTITY))
    }

    private fun setArticleListInDatabase(list: List<NewsPublisherData>) {
        `when`(database.getArticlesDao().getAllArticles()).thenReturn(Flowable.just(list))
    }

    companion object {
        val LIST_DATA = TestUtil.getDummyListData()
        val LIST_ENTITY = TestUtil.getDummyList()
    }
}