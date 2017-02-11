package com.blaizmiko.popcornapp.ui.fragments.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpDelegate;

public abstract class BaseMvpFragment extends BaseFragment {

    private boolean mIsStateSaved;
    private MvpDelegate<? extends BaseMvpFragment> mMvpDelegate;

    public BaseMvpFragment() {
    }

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        this.mIsStateSaved = false;
        this.getMvpDelegate().onAttach();
    }

    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mIsStateSaved = true;
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

        if (this.mIsStateSaved) {
            this.mIsStateSaved = false;
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
        if (this.mMvpDelegate == null) {
            this.mMvpDelegate = new MvpDelegate(this);
        }

        return this.mMvpDelegate;
    }
}
