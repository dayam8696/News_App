package com.example.newsly

import android.graphics.pdf.PdfDocument.Page
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=IN&apiKey=baff71ecf29a4bce9b2051f113bca2c1
//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=baff71ecf29a4bce9b2051f113bca2c1

const val BASE_URL = "https://newsapi.org/"
const val API_Key = "baff71ecf29a4bce9b2051f113bca2c1"

interface NewsInterface{
    @GET("v2/top-headlines?apiKey=$API_Key")
 fun getHeadlines(@Query("country") country :String, @Query("page")page:Int) :Call<News>
}
// https://newsapi.org/v2/top-headlines?apiKey=baff71ecf29a4bce9b2051f113bca2c1&country=in&page=1

object NewsService {
    val newsInstance : NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}