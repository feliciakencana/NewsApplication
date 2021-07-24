package com.example.newsappver2.Common;


import com.example.newsappver2.Interface.NewsService;
import com.example.newsappver2.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL="https://newsapi.org/";
    public static final String API_KEY="358c3733de5d416b9d4f55ba03f08f6e";

    public static NewsService getNewsService(){
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    //"https://newsapi.org/v2/top-headlines?

    public static String getAPIURL(String country, String sortBy, String apiKey){
        StringBuilder apiUrl= new StringBuilder("https://newsapi.org/v2/top-headlines?country=");
        return apiUrl.append(country).append("&sortBy=popularity").append("&apiKey=").append(apiKey).toString();
    }

    public static String getAPIURLCategory(String country, String sortBy, String category, String apiKey){
        StringBuilder apiUrl= new StringBuilder("https://newsapi.org/v2/top-headlines?country=");
        return apiUrl.append(country).append("&category=").append(category).append("&sortBy=popularity").append("&apiKey=").append(apiKey).toString();
    }

    public static String searchResult(String keywords, String sortBy, String apiKey){
        StringBuilder apiUrl= new StringBuilder("https://newsapi.org/v2/everything?qInTitle=");
        return apiUrl.append(keywords).append("&apiKey=").append(apiKey).append("&sortBy=popularity").toString();
    }
}