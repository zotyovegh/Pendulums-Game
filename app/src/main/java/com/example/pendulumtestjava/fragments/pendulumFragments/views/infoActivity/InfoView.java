package com.example.pendulumtestjava.fragments.pendulumFragments.views.infoActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pendulumtestjava.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class InfoView extends YouTubeBaseActivity {

    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_InfoDialog);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube);
        
        mYouTubePlayerView = findViewById(R.id.youtubePlay);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                Intent intent = getIntent();
                type = intent.getStringExtra("type");
                if(type.equals("single"))
                {
                    youTubePlayer.loadVideo("_4ICFmAr_DQ");
                }else if(type.equals("double"))
                {
                    youTubePlayer.loadVideo("QXf95_EKS6E");
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(InfoView.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        };


        mYouTubePlayerView.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);


    }
}
