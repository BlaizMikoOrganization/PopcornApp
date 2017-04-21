package com.blaizmiko.popcornapp.ui.actors.details.biography;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.FormatUtil;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.blaizmiko.popcornapp.data.models.actors.DetailedActorModel;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class BiographyActorPresenter extends BaseMvpPresenter<BiographyActorView>{

    @Inject
    MovieDbApi movieDbApi;

    BiographyActorPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadActorPhoto(final int actorId) {
        getViewState().startLoad();
        final Subscription actorPhotoSubcription = movieDbApi.getActorPhoto(actorId)
                .flatMap(imagesActorResponse -> Observable.from(imagesActorResponse.getProfiles()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imageModelList -> {
                    getViewState().showPhotos(imageModelList);
                }, error -> {
                    getViewState().showError();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(actorPhotoSubcription);
    }

    public void loadActorBiography(final int actorId) {
        getViewState().startLoad();
        final Subscription actorInfoSubscription = movieDbApi.getActorInfo(actorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(actor -> formatOverview(actor)
                , error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(actorInfoSubscription);
    }

    private void formatOverview(DetailedActorModel actor) {
        getViewState().showAge(FormatUtil.calculatePassedYearsFromCurrent(actor.getBirthday()));
        getViewState().showGender(FormatUtil.parseGender(actor.getGender()));
        getViewState().showBirthDate(FormatUtil.parseDateToMaterialFormat(actor.getBirthday(), FormatUtil.ResultMaterialDateType.FULL));
        getViewState().showDeathDate(actor.getDeathday().isEmpty()? StringUtil.NOT_AVAILABLE_STRING:FormatUtil.parseDateToMaterialFormat(actor.getBirthday(), FormatUtil.ResultMaterialDateType.FULL));
        getViewState().showBiography(actor.getBiography());
        getViewState().showBirthPlace(actor.getPlaceOfBirth());
    }

}
