package com.blaizmiko.popcornapp.ui.actors.details;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.models.actors.ActorModel;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;

import butterknife.BindView;

public class ActorDetailsActivity extends BaseMvpActivity implements ActorDetailsView{

    @InjectPresenter
    ActorDetailsPresenter actorDetailsPresenter;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    //Life cycle
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);
    }

    @Override
    protected void bindViews() {
        setToolbar(toolbar);
        setToolbarTitle("");
        setToolbarDisplayHomeButtonEnabled(true);

        actorDetailsPresenter.loadActorDetails(2524);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setActorDetails(final ActorModel actor) {
        //setToolbarTitle(actor.getName());
    }
}
