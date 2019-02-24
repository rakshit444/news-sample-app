package com.rakshitjain.presentation.news

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rakshitjain.presentation.entities.Data
import kotlinx.android.synthetic.main.news_articles.*
import org.koin.android.viewmodel.ext.android.viewModel
import rakshitjain.news_sample_app.R

class NewsActivity : AppCompatActivity() {

    private val newsList: NewsViewModel by viewModel()
    private lateinit var listAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_articles)
        listAdapter = NewsListAdapter()

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = listAdapter
        newsList.fetchNews()
    }

    override fun onStart() {
        super.onStart()
        newsList.getNewsLiveData().observe(this, Observer {
            when (it) {
                is Data.ERROR -> {
                    //Error handling
                }
                is Data.LOADING -> {
                    //Progress
                }
                is Data.SUCCESS -> {
                    it.data?.articles?.let { it1 -> listAdapter.updateList(it1) }
                }
            }
        })
    }
}