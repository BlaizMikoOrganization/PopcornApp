package com.blaizmiko.popcornapp.ui.fragments.movies;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.movie.ShortMovie;
import com.blaizmiko.popcornapp.presentation.presenters.movies.MoviesPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.MoviesView;
import com.blaizmiko.popcornapp.ui.adapters.ShortMoviesAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends BaseMvpFragment implements MoviesView {

    @InjectPresenter
    MoviesPresenter mMoviesPresenter;

    //Bind views
    @BindView(R.id.fragment_movies_recycler_view)
    protected RecyclerView mRecyclerView;

    //Get instance of MoviesFragment
    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    //Life cycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        final int testMoviesAmount = 6;
        final ShortMoviesAdapter adapter = new ShortMoviesAdapter(generateTestMovies(testMoviesAmount), getContext());

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    protected void bindViews() {}

    //Generate testMovie List
    private List<ShortMovie> generateTestMovies(int testMoviesAmount) {
        final String title = "Test Title";
        ArrayList<ShortMovie> arrayList = new ArrayList<>();

        for (int i = 0; i < testMoviesAmount; i++) {
            ShortMovie movie = new ShortMovie();
            movie.setTitle(title + (i + 1));
            movie.setPosterPath("" + (i % 2 == 0 ? R.drawable.test_poster_1 : R.drawable.test_poster_2));
            arrayList.add(movie);
        }

        return arrayList;
    }
}
