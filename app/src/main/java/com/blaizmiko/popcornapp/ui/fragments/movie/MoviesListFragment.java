package com.blaizmiko.popcornapp.ui.fragments.movie;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.Movie.ShortMovie;
import com.blaizmiko.popcornapp.ui.adapters.ShortMoviesAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListFragment extends BaseFragment {

    //Bind views
    @BindView(R.id.movies_recycler_view)
    protected RecyclerView mRecyclerView;

    //Get instance of MoviesListFragment
    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    //Life cycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        ButterKnife.bind(this, view);

        int testMoviesAmount = 6;
        ShortMoviesAdapter adapter = new ShortMoviesAdapter(generateTestMovies(testMoviesAmount), getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

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
