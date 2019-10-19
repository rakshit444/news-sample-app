package com.rakshitjain.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.rakshitjain.data.TestUtils
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArticleDaoUnitTest{

    private lateinit var db: NewsDatabase
    private lateinit var userDao: ArticlesDao

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, NewsDatabase::class.java).build()
        userDao = db.getArticlesDao()
    }

    @After
    @Throws(IOException::class)
    fun end(){
        db.close()
    }

    @Test
    fun addArticlesInDatabase(){
        val testData = TestUtils.createTestDataForNews()
        userDao.saveAllArticles(testData)

        val allArticles = userDao.getAllArticles().blockingFirst()
        assert(allArticles!!.isEmpty())
    }

    @Test
    fun clearArticlesDatabase(){
        val testData = TestUtils.createTestDataForNews()
        userDao.saveAllArticles(testData)

        userDao.clear();
        val allArticles = userDao.getAllArticles().blockingFirst()
        assert(allArticles == null)
    }

    @Test
    fun writeArticleAndReadArticle(){
        val testData = TestUtils.createTestDataForNews()
        userDao.saveAllArticles(testData)
        val allArticles = userDao.getAllArticles().blockingFirst()
        assertThat(allArticles!![0].name,equalTo("sdcs"))
    }
}