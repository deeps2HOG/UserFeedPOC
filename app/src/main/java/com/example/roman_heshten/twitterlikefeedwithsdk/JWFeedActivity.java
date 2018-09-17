package com.example.roman_heshten.twitterlikefeedwithsdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//The end goal is to create a User-Feed (sort of like Facebook user feed).
//Right now I am trying to show list of videos in Recycler View. (A Feed is basically a RecyclerView which contains ViewHolders of various types)
public class JWFeedActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    JWFeedAdapter adapter;

    List<String> feedUrls = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jw_feed);
        recyclerView = findViewById(R.id.jw_feed);

        feedUrls.add("http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8");
        feedUrls.add("http://playertest.longtailvideo.com/jwpromo/jwpromo.m3u8");
        feedUrls.add("http://playertest.longtailvideo.com/adaptive/more4plus1/playlist.m3u8");
        feedUrls.add("http://content.bitsontherun.com/videos/3XnJSIm4-52qL9xLP.mp4");
        feedUrls.add("http://cdn-videos.akamaized.net/btv/desktop/fastly/us/live/primary.m3u8");
        feedUrls.add("http://content.jwplatform.com/videos/nhYDGoyh-el5vTWpr.mp4");
        feedUrls.add("http://content.bitsontherun.com/videos/3XnJSIm4-injeKYZS.mp4");

        adapter = new JWFeedAdapter(this, feedUrls);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}
