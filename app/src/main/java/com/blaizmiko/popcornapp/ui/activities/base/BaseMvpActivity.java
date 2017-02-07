package com.blaizmiko.popcornapp.ui.activities.base;

import android.os.Bundle;

import com.arellomobile.mvp.MvpDelegate;

public abstract class BaseMvpActivity extends BaseActivity {

    private MvpDelegate<? extends BaseMvpActivity> mMvpDelegate;

    public BaseMvpActivity() {
    }

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
    }

    protected void onResume() {
        super.onResume();
        this.getMvpDelegate().onAttach();
    }

    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        this.getMvpDelegate().onSaveInstanceState(outState);
        this.getMvpDelegate().onDetach();
    }

    protected void onStop() {
        super.onStop();
        this.getMvpDelegate().onDetach();
    }

    protected void onDestroy() {
        super.onDestroy();

        if (this.isFinishing()) {
            this.getMvpDelegate().onDestroy();
        }
    }

    public MvpDelegate getMvpDelegate() {
        if (this.mMvpDelegate == null) {
            this.mMvpDelegate = new MvpDelegate(this);
        }

        return this.mMvpDelegate;
    }
}
