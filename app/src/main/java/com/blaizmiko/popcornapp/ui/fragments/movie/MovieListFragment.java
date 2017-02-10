package com.blaizmiko.popcornapp.ui.fragments.movie;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.Movie.Movie;
import com.blaizmiko.popcornapp.models.Movie.ShortMovie;
import com.blaizmiko.popcornapp.ui.adapters.ShortMoviesAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MovieListFragment extends BaseFragment {

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        RecyclerView recyclerView = ButterKnife.findById(view, R.id.recycler_view);
        ArrayList<ShortMovie> arrayList = new ArrayList<>();

        ShortMovie testMovie = new ShortMovie();
        testMovie.setTitle("pish1");
        arrayList.add(testMovie);

        ShortMovie testMovie2 = new ShortMovie();
        testMovie2.setTitle("pish2");
        arrayList.add(testMovie2);

        ShortMovie testMovie3 = new ShortMovie();
        testMovie.setTitle("pish3");
        arrayList.add(testMovie);

        ShortMovie testMovie4 = new ShortMovie();
        testMovie.setTitle("pish4");
        arrayList.add(testMovie);

        ShortMoviesAdapter adapter = new ShortMoviesAdapter(arrayList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}
