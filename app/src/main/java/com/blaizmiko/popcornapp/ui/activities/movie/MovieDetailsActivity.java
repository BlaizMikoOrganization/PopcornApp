package com.blaizmiko.popcornapp.ui.activities.movie;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.activities.base.BaseActivity;
import com.blaizmiko.popcornapp.ui.adapters.movies.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.fragments.movies.MovieDetailsFragment;

public class MovieDetailsActivity extends BaseActivity {

    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    private GenresTagsAdapter mGenresTagsAdapter;
    private int mMovieId;
    private final int mStoryLineTextViewLinesMin = 3;
    private final int mStoryLineTextViewLinesMax = 8;
    private boolean mIsStoryLineTextViewOpen = false;
    public static final String mBundleArgumentId = "ID";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_movies);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        System.out.println("action bar " +getSupportActionBar());
        if (getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mMovieId = getIntent().getIntExtra("ID", 14564);
        System.out.println("movie id " +mMovieId);


        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment fragment = MovieDetailsFragment.newInstance();
        Bundle testBundle = new Bundle();
        testBundle.putInt("ID", mMovieId);
        fragment.setArguments(testBundle);
        fragmentManager.beginTransaction().replace(R.id.pish, fragment).commitNow();
    }

    @Override
    protected void bindViews() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
