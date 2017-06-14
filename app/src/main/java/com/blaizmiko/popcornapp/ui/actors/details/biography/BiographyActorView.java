package com.blaizmiko.popcornapp.ui.actors.details.biography;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.db.models.movies.ImageDBModel;

import java.util.List;

public interface BiographyActorView extends MvpView{

    void showAge(int age);
    void showGender(String gender);
    void showBirthDate(String birthDate);
    void showDeathDate(String deathDate);
    void showBirthPlace(String birthPlace);
    void showBiography(String biography);
    void showPhotos(List<ImageDBModel> images);

    void startLoad();
    void finishLoad();
    void showError();
}
