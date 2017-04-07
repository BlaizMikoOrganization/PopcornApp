package com.blaizmiko.popcornapp.ui.all.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpDelegate;

public abstract class BaseMvpFragment extends BaseFragment {

    private boolean isStateSaved;
    private MvpDelegate<? extends BaseMvpFragment> mvpDelegate;

    public BaseMvpFragment() {
    }

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        this.isStateSaved = false;
        this.getMvpDelegate().onAttach();
    }

    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        this.isStateSaved = true;
        this.getMvpDelegate().onSaveInstanceState(outState);
        this.getMvpDelegate().onDetach();
    }

    public void onStop() {
        super.onStop();
        this.getMvpDelegate().onDetach();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.getMvpDelegate().onDetach();
        this.getMvpDelegate().onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();

        if (this.isStateSaved) {
            this.isStateSaved = false;
        } else {
            boolean anyParentIsRemoving = false;

            for (Fragment parent = this.getParentFragment(); !anyParentIsRemoving && parent != null; parent = parent.getParentFragment()) {
                anyParentIsRemoving = parent.isRemoving();
            }

            if (this.isRemoving() || anyParentIsRemoving || this.getActivity().isFinishing()) {
                this.getMvpDelegate().onDestroy();
            }
        }
    }

    public MvpDelegate getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new MvpDelegate(this);
        }
        return this.mvpDelegate;
    }
}
