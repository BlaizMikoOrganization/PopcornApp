package com.blaizmiko.popcornapp.ui.all.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.BindView;

public class TrailersActivity extends BaseMvpActivity implements YouTubePlayer.OnInitializedListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers);
    }

    @Override
    protected void bindViews() {
        setSupportActionBar(toolbar);
        setToolbarTitle("");
        setToolbarDisplayHomeButtonEnabled(true);
        YouTubePlayerSupportFragment fragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_trailers_youtube_player);
        fragment.initialize(Constants.YouTubeApi.API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(getIntent().getStringExtra(Constants.Extras.VIDEO_URL));
        youTubePlayer.play();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final String errorMsg = "Sorry an error has occurred while playback trailer. Make sure you have a YouTube player";
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }
}
