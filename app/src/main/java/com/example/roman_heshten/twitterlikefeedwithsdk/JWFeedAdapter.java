package com.example.roman_heshten.twitterlikefeedwithsdk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.longtailvideo.jwplayer.JWPlayerView;

import java.util.List;

public class JWFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> feedUrl;

    public JWFeedAdapter(Context context, List<String> feedUrl) {
        this.context = context;
        this.feedUrl = feedUrl;
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout container;
        JWPlayerView feedView;

        public FeedViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.jw_player_container);
            feedView = itemView.findViewById(R.id.jw_player_view_holder);
            container.setOnClickListener(this);
            feedView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //the code works if I replace feedView with another view(such as button) in R.layout.jw_feed_view_holder
            Toast.makeText(v.getContext(), "Clicked" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }

        public void setData(String url) {
            //todo -- will implement it later . First I should be able to detect clicks. Unable to detect clicks
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedViewHolder(LayoutInflater.from(context).inflate(R.layout.jw_feed_view_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FeedViewHolder feedViewHolder = (FeedViewHolder)holder;
        feedViewHolder.setData(feedUrl.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return feedUrl.size();
    }


}
