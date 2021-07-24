package com.example.newsappver2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingFragment extends Fragment {
    KenBurnsView kbv;
    SpotsDialog dialog;
    NewsService mService;
    TextView top_author,top_title;
    SwipeRefreshLayout swipeRefreshLayout;
    FrameLayout img_layout;
    String country="", sortBy="",webHotUrl="",keywords="";
    ListNewsAdapter adapter;
    RecyclerView lstNews;
    RecyclerView.LayoutManager layoutManager;

    public TrendingFragment() {

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
                loadNews(country,true);
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
        loadNews(country,false);
    }

    private void loadNews(String country, boolean isRefreshed){
        if(!isRefreshed){
            dialog.show();
            mService.getNewestArticles(Common.getAPIURL(country,sortBy,Common.API_KEY)).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    dialog.dismiss();
                    /*//get first article
                    Picasso.get().load(response.body().getArticles().get(0).getUrlToImage()).into(kbv);
                    top_title.setText(response.body().getArticles().get(0).getTitle());
                    top_author.setText(response.body().getArticles().get(0).getAuthor());

                    webHotUrl=response.body().getArticles().get(0).getUrl();

                    List<Article> removeFirstItem= response.body().getArticles();
                    //article pertama udh ditunjukin di diagonal view, jdinya perlu di remove
                    removeFirstItem.remove(0);*/
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
            mService.getNewestArticles(Common.getAPIURL(country,sortBy,Common.API_KEY)).enqueue(new Callback<News>() {
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
