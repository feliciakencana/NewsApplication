package com.example.newsappver2.Interface;

import com.example.newsappver2.Common.Common;
import com.example.newsappver2.Model.News;
import com.example.newsappver2.Model.Website;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {
    //error hilangin apikey
    //v2/sources?apiKey=358c3733de5d416b9d4f55ba03f08f6e
    //v2/everything?q=bitcoin q itu keyword buat nyari article
    //https://newsapi.org/v2/everything?
    //v2/sources?language=en&apiKey=
    //v2/top-headlines?country=us&apiKey=
    //error cari cara buat gnti input country, semenatara pke us
    @GET("v2/top-headlines?country=us&apiKey="+ Common.API_KEY)
    Call<Website>getSources();
    @GET
    Call<News>getNewestArticles(@Url String url);
}
