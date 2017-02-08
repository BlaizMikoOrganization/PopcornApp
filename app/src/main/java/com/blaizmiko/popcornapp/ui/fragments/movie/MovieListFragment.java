package com.blaizmiko.popcornapp.ui.fragments.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseFragment;

public class MovieListFragment extends BaseFragment {

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }
}
