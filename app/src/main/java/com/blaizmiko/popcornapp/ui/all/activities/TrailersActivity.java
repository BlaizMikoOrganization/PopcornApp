package com.blaizmiko.popcornapp.ui.all.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.BindView;

public class TrailersActivity extends BaseMvpActivity implements YouTubePlayer.OnInitializedListener{
    //Binding
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    //Lifecycle
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers);
    }

    @Override
    protected void bindViews() {
        toolbar.setTitle("");
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);

        final YouTubePlayerSupportFragment fragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_trailers_youtube_player);
        fragment.initialize(Constants.YouTubeApi.API_KEY, this);
    }

    //Callbacks
    @Override
    public void onInitializationSuccess(final YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, final boolean b) {
        youTubePlayer.loadVideo(getIntent().getStringExtra(Constants.Extras.VIDEO_URL));
        youTubePlayer.play();
    }

    @Override
    public void onInitializationFailure(final YouTubePlayer.Provider provider, final YouTubeInitializationResult youTubeInitializationResult) {
        final String errorMsg = "Something go wrong. Do you have a YouTube player?";
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
