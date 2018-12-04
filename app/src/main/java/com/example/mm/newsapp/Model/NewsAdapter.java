package com.example.mm.newsapp.Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mm.newsapp.R;
import com.example.mm.newsapp.activity.NewsExplore;

import java.util.ArrayList;

import static com.example.mm.newsapp.Model.Constants.KEY_NEWS_URL;
import static com.example.mm.newsapp.Model.Constants.MY_GITHUB_REPOSITORIES;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private Context mContext;
    private ArrayList<NewsData> dataList ;

    public NewsAdapter(Context mContext, ArrayList<NewsData> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = inflate.inflate(R.layout.item, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {

        final NewsData newsData = dataList.get(position);
        holder.titleView.setText(newsData.getNewsTitle());
        holder.detailsView.setText(newsData.getNewsDetail());
        Glide.with(mContext).load(newsData.getNewsImageUrl()).into(holder.imgView);


        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, NewsExplore.class);
                intent.putExtra(KEY_NEWS_URL, newsData.getNewsUrl());
                context.startActivity(intent);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                String shareBody = "\nApp project is available at :" + "\n\nGitHub : " + MY_GITHUB_REPOSITORIES ;
                intentShare.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                intentShare.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(intentShare, "Share via"));
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView titleView, detailsView, hiddenUrl;
        Button explore, share;

        public NewsHolder(View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.imgID);
            titleView = itemView.findViewById(R.id.titleTextViewID);
            detailsView = itemView.findViewById(R.id.detailsTextViewID);
            explore = itemView.findViewById(R.id.exploreButtonId);
            share = itemView.findViewById(R.id.shareButtonId);
            hiddenUrl = itemView.findViewById(R.id.hiddenUrl);
        }
    }

}
