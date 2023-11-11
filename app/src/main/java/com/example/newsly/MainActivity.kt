 package com.example.newsly

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsly.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MainActivity : AppCompatActivity() {
     private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

     lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        prepareRecyclerView()

        getNews()
    }

     private fun prepareRecyclerView() {
         newsAdapter = NewsAdapter()
         binding.newsList.apply {
             adapter = newsAdapter
             layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
         }
     }

     // @SuppressLint("SuspiciousIndentation")
    private fun getNews() {
      val news = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(@SuppressLint("SuspiciousIndentation")
        object: Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
             if (news!= null){
                 Log.d("DAYAM" , news.toString())
                val articleList = news.articles
                 newsAdapter.setList(articleList)
             }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("DAYAM" , "Error in fetching news")


            }
        })
    }
}