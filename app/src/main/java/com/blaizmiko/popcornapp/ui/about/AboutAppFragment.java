package com.blaizmiko.popcornapp.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseFragment;

public class AboutAppFragment extends BaseFragment {

    public static AboutAppFragment newInstance() {
        return new AboutAppFragment();
    }

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_app, container, false);
    }

    @Override
    protected void bindViews() {

    }
}
