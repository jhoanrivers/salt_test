package com.example.salt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salt.R;
import com.example.salt.model.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MovieHolder> {


    List<News> movieList;
    Context context;


    public NewsAdapter(List<News> movieList, Context context){

        this.movieList = movieList;
        this.context = context;
    }



    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_movies,parent,false);
        MovieHolder mh = new MovieHolder(v);
        return mh;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        holder.tvTitle.setText(movieList.get(position).getTitle());
        holder.tvDescription.setText(movieList.get(position).getDescription());
        holder.tvPublished.setText(movieList.get(position).getPublishedAt());
        Glide.with(context).load(movieList.get(position).getUrlToImage()).into(holder.ivImageUrl);


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription, tvPublished;
        ImageView ivImageUrl;

        public MovieHolder(View v) {
            super(v);

            tvTitle  = v.findViewById(R.id.tv_title);
            tvDescription = v.findViewById(R.id.tv_description);
            tvPublished = v.findViewById(R.id.tv_publisheday);
            ivImageUrl = v.findViewById(R.id.iv_imageurl);

        }
    }
}
