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

import butterknife.BindView;

public class MovieDetailsActivity extends BaseActivity {

    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    private final int mDefaultMovieDetailsId = 0;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final int movieId = getIntent().getIntExtra(MovieDetailsFragment.mBundleArgumentId, mDefaultMovieDetailsId);
        Bundle movieIdBundle = new Bundle();
        movieIdBundle.putInt(MovieDetailsFragment.mBundleArgumentId, movieId);

        final Fragment fragment = MovieDetailsFragment.newInstance();
        fragment.setArguments(movieIdBundle);
        fragmentManager.beginTransaction().replace(R.id.activity_detail_movies_container_layout, fragment).commitNow();
    }

    @Override
    protected void bindViews() {
        setToolbar(mToolbar);
        setToolbarDisplayHomeButtonEnabled(true);
        setToolbarDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
