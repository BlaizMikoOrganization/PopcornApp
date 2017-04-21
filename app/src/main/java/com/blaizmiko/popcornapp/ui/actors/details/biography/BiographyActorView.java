package com.blaizmiko.popcornapp.ui.actors.details.biography;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.images.ImageModel;

import java.util.List;

public interface BiographyActorView extends MvpView{

    void showAge(int age);
    void showGender(String gender);
    void showBirthDate(String birthDate);
    void showDeathDate(String deathDate);
    void showBirthPlace(String birthPlace);
    void showBiography(String biography);
    void showPhotos(List<ImageModel> images);

    void startLoad();
    void finishLoad();
    void showError();
}
