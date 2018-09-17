package com.example.roman_heshten.twitterlikefeedwithsdk;

import com.longtailvideo.jwplayer.configuration.PlayerConfig;

/**
 * Class is using for holding {@link PlayerConfig} for every list item
 *
 * */
public class Feed {

    private final PlayerConfig mPlayerConfig;

    Feed(String videoUrl) {
        mPlayerConfig = new PlayerConfig.Builder()
                .file(videoUrl)
                .controls(true)
                .build();
    }

    public PlayerConfig getVideoConfig() {
        return mPlayerConfig;
    }
}
