package com.rakshitjain.presentation.news

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.news_articles.*
import org.koin.android.viewmodel.ext.android.viewModel
import rakshitjain.news_sample_app.R
import com.rakshitjain.presentation.entities.Status

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
            when (it?.responseType) {
                Status.ERROR -> {
                    //Error handling
                }
                Status.LOADING -> {
                    //Progress
                }
                Status.SUCCESSFUL -> {
                    // On Successful response
                }
            }
            it?.data?.let { response ->
                listAdapter.updateList(response.articles)
            }
        })
    }
}