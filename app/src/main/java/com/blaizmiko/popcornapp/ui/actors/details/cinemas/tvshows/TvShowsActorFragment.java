package com.blaizmiko.popcornapp.ui.actors.details.cinemas.tvshows;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.actors.details.cinemas.CinemasActorFragment;
import com.blaizmiko.popcornapp.ui.all.adapters.ActorJobCinemasAdapter;

public class TvShowsActorFragment extends CinemasActorFragment {
    public static TvShowsActorFragment newInstance() {
        return new TvShowsActorFragment();
    }

    public static final String TITLE = "TvShows";

    @Override
    protected void bindViews() {
        super.bindViews();
        cinemasActorPresenter.loadActorTvShows(getArguments().getInt(Constants.Extras.ID));
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final ActorJobCinemasAdapter.CinemaItem item = ((ActorJobCinemasAdapter) adapter).getItemByPosition(position);
        ActivityNavigator.startDetailsTvShowActivity(getActivity().getApplicationContext(),
                item.getId(),
                item.getTitle());
    }
}
