package com.example.newsappver2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsappver2.ArticleDetails;
import com.example.newsappver2.Common.ISO8601DateParse;
import com.example.newsappver2.Model.Article;
import com.example.newsappver2.Model.News;
import com.example.newsappver2.Model.Website;
import com.example.newsappver2.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {
    private List<Article> articleList;
    private News news;
    private ItemClickListener itemClickListener;
    Context context;

    public SearchNewsAdapter(List<Article> articleList,Context context){
        this.articleList=articleList;
        this.context=context;
    }

    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.search_layout,parent,false);
        return new SearchNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, final int position) {
        Picasso.get().load(articleList.get(position).getUrlToImage()).into(holder.article_image);
        if(articleList.get(position).getTitle().length()>65)
            holder.article_title.setText(articleList.get(position).getTitle().substring(0,65)+"...");
            //substring itu return string dri posisi 0-65
            //di case ini title cm ditunjukan batas maks 65 huruf, lbh dri itu sisa belakangnya gk ditulis
        else
            holder.article_title.setText(articleList.get(position).getTitle());
        //NewsAPI pke ISO 8601
        if(articleList.get(position).getPublishedAt()!=null){
            Date date=null;
            SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );
            try{
                date= ISO8601DateParse.parse(articleList.get(position).getPublishedAt());
            }catch(ParseException ex){
                ex.printStackTrace();
            }
            holder.article_time.setReferenceTime(date.getTime());
        }
       // holder.article_source.setText(website.getSource(articleList.get(position)).getName());

        //set event click
        context=holder.itemView.getContext();
        /*holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent detail=new Intent(context, DetailArticle.class);
                detail.putExtra("webUrl",articleList.get(position).getUrl());
                context.startActivity(detail);
            }
        });*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail=new Intent(context, ArticleDetails.class);
                detail.putExtra("webUrl",articleList.get(position).getUrl());
                context.startActivity(detail);
            }
        });

    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class SearchNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemClickListener itemClickListener;
        TextView article_title,article_source;
        RelativeTimeTextView article_time;
        KenBurnsView article_image;
        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            article_image=itemView.findViewById(R.id.searchArticle_image);
            article_title=itemView.findViewById(R.id.searchArticle_title);
            article_time=itemView.findViewById(R.id.searchArticle_time);
            article_source=itemView.findViewById(R.id.searchArticle_source);
        }


        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }
    public interface ItemClickListener{
        void onClick(View view, int position, boolean isLongClick);
    }
}
