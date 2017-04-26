package com.blaizmiko.popcornapp.ui.actors.details.biography;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.images.ImageModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.photos.PhotosAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import java.util.List;

import butterknife.BindView;

public class BiographyActorFragment extends BaseMvpFragment implements BiographyActorView, StorylineView, View.OnClickListener, RecyclerViewListeners.OnItemClickListener {
    public static BiographyActorFragment newInstance() {
        return new BiographyActorFragment();
    }

    @BindView(R.id.text_view_actor_biography_age)
    protected TextView ageTextView;
    @BindView(R.id.text_view_actor_biography_gender)
    protected TextView genderTextView;
    @BindView(R.id.text_view_actor_biography_birth_date)
    protected TextView birthDateTextView;
    @BindView(R.id.text_view_actor_biography_death_date)
    protected TextView deathDateTextView;
    @BindView(R.id.text_view_actor_biography_place)
    protected TextView birthPlaceTextView;
    @BindView(R.id.text_view_actor_biography_biography)
    protected TextView biographyTextView;
    @BindView(R.id.recycler_view_actor_biography_photos)
    protected RecyclerView photosRecyclerView;

    private int actorId;
    public static final String TITLE = "Biography";
    private PhotosAdapter photosAdapter;

    @InjectPresenter
    BiographyActorPresenter biographyActorPresenter;
    @InjectPresenter
    StorylinePresenter storylinePresenter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actorId = getArguments().getInt(Constants.Extras.ID);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actor_biography, container, false);
    }

    @Override
    protected void bindViews() {
        final Context context = getActivity().getApplicationContext();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        photosAdapter = new PhotosAdapter(context, PhotosAdapter.PhotoType.VERTICAL);
        photosRecyclerView.setLayoutManager(linearLayoutManager);
        photosRecyclerView.setAdapter(photosAdapter);
        photosAdapter.setItemClickListener(this);

        biographyActorPresenter.loadActorBiography(actorId);
        biographyActorPresenter.loadActorPhoto(actorId);
    }

    @Override
    public void showAge(final int age) {
        ageTextView.setText(String.valueOf(age));
    }

    @Override
    public void showGender(final String gender) {
        genderTextView.setText(gender);
    }

    @Override
    public void showBirthDate(final String birthDate) {
        birthDateTextView.setText(birthDate);
    }

    @Override
    public void showDeathDate(final String deathDate) {
        deathDateTextView.setText(deathDate);
    }

    @Override
    public void showBirthPlace(final String birthPlace) {
        birthPlaceTextView.setText(birthPlace);
    }

    @Override
    public void showBiography(final String biography) {
        biographyTextView.setText(biography);
        biographyTextView.setOnClickListener(this);
        storylinePresenter.setExpandedLinesNumber(biographyTextView.getLineCount());
        storylinePresenter.calculateNewSize();
    }

    @Override
    public void showPhotos(final List<ImageModel> images) {
        photosAdapter.update(images);
    }

    @Override
    public void startLoad() {

    }

    @Override
    public void finishLoad() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void changeStorylineSize(final int lines) {
        biographyTextView.setLines(lines);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.text_view_actor_biography_biography:
                storylinePresenter.calculateNewSize();
                break;
        }
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        Log.d("BiographyActorFragment", "" +getResources().getResourceName(view.getId()));
        switch (view.getId()) {
            case R.id.image_view_adapter_movie_details_photo_item_photo:
                final List<ImageModel> images = ((PhotosAdapter) adapter).getAllItems();
                final String [] imageUrls = new String[images.size()];
                for (int i = 0; i < images.size(); i++) {
                    imageUrls[i] = images.get(i).getFilePath();
                }
                ActivityNavigator.startGalleryActivity(getActivity().getApplicationContext(), position, imageUrls, "", "");
        }
    }
}
