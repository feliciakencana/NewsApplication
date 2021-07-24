package com.example.newsappver2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsappver2.Adapter.ListNewsAdapter;
import com.example.newsappver2.ArticleDetails;
import com.example.newsappver2.Common.Common;
import com.example.newsappver2.Interface.NewsService;
import com.example.newsappver2.Model.Article;
import com.example.newsappver2.Model.News;
import com.example.newsappver2.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {
    KenBurnsView kbv;
    SpotsDialog dialog;
    NewsService mService;
    TextView top_author,top_title;
    SwipeRefreshLayout swipeRefreshLayout;
    FrameLayout img_layout;
    String country="", sortBy="",webHotUrl="",keywords="", category="";
    ListNewsAdapter adapter;
    RecyclerView lstNews;
    RecyclerView.LayoutManager layoutManager;

    public EntertainmentFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        //service
        mService= Common.getNewsService();
        dialog= new SpotsDialog(this.getContext());
        //view
        swipeRefreshLayout= v.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(country,category,true);
            }
        });

        /*img_layout=v.findViewById(R.id.img_layout);
        img_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail=new Intent(getActivity(), ArticleDetails.class);
                detail.putExtra("webUrl",webHotUrl);
                startActivity(detail);
            }
        });

        kbv=v.findViewById(R.id.top_image);
        top_author=v.findViewById(R.id.top_author);
        top_title=v.findViewById(R.id.top_title);*/

        lstNews=v.findViewById(R.id.first_news);
        lstNews.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this.getContext());
        lstNews.setLayoutManager(layoutManager);

        country="us";
        category="entertainment";
        loadNews(country,category,false);
    }

    private void loadNews(String country, String category, boolean isRefreshed){
        if(!isRefreshed){
            dialog.show();
            mService.getNewestArticles(Common.getAPIURLCategory(country,sortBy, category,Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();
                    List<Article> articleList= response.body().getArticles();
                    adapter=new ListNewsAdapter(articleList,getActivity());
                    adapter.notifyDataSetChanged();
                    lstNews.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
        }else{
            dialog.show();
            mService.getNewestArticles(Common.getAPIURLCategory(country,sortBy, category,Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();
                    List<Article> articleList= response.body().getArticles();
                    adapter=new ListNewsAdapter(articleList,getActivity());
                    adapter.notifyDataSetChanged();
                    lstNews.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
            swipeRefreshLayout.setRefreshing(false);
        }
    }

}
