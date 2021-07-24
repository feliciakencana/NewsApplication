package com.example.newsappver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.newsappver2.Adapter.ListNewsAdapter;
import com.example.newsappver2.Adapter.SearchNewsAdapter;
import com.example.newsappver2.Common.Common;
import com.example.newsappver2.Interface.NewsService;
import com.example.newsappver2.Model.Article;
import com.example.newsappver2.Model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchNews extends AppCompatActivity {
    SpotsDialog dialog;
    NewsService mService;
    TextView top_author,top_title;
    SwipeRefreshLayout swipeRefreshLayout;
    String sortBy="",webHotUrl="",keywords="";
    SearchNewsAdapter adapter;
    RecyclerView searchNews;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
        mService= Common.getNewsService();
        Intent intent=getIntent();
        keywords=intent.getStringExtra(MainActivity.SEARCH_KEYWORDS);
        dialog= new SpotsDialog(this);
        searchNews=findViewById(R.id.search_news);
        searchNews.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        searchNews.setLayoutManager(layoutManager);
        swipeRefreshLayout=findViewById(R.id.search_swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(keywords,true);
            }
        });
        loadNews(keywords,false);


    }

    private void loadNews(String keywords,boolean isRefreshed){
        if(!isRefreshed){
            dialog.show();
            mService.getNewestArticles(Common.searchResult(keywords,sortBy,Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();
                    //get first article
                    /*Picasso.get().load(response.body().getArticles().get(0).getUrlToImage()).into(kbv);
                    top_title.setText(response.body().getArticles().get(0).getTitle());
                    top_author.setText(response.body().getArticles().get(0).getAuthor());

                    webHotUrl=response.body().getArticles().get(0).getUrl();*/

                    List<Article> articles= response.body().getArticles();
                    //article pertama udh ditunjukin di diagonal view, jdinya perlu di remove
                    //removeFirstItem.remove(0);
                    adapter=new SearchNewsAdapter(articles,getBaseContext());
                    adapter.notifyDataSetChanged();
                    searchNews.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
        }else{
            dialog.show();
            mService.getNewestArticles(Common.searchResult(keywords,sortBy,Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();
                    //get first article
                    /*Picasso.get().load(response.body().getArticles().get(0).getUrlToImage()).into(kbv);
                    top_title.setText(response.body().getArticles().get(0).getTitle());
                    top_author.setText(response.body().getArticles().get(0).getAuthor());

                    webHotUrl=response.body().getArticles().get(0).getUrl();*/

                    List<Article> articles= response.body().getArticles();
                    //article pertama udh ditunjukin di diagonal view, jdinya perlu di remove
                    //removeFirstItem.remove(0);
                    adapter=new SearchNewsAdapter(articles,getBaseContext());
                    adapter.notifyDataSetChanged();
                    searchNews.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }

            });
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}