package com.blaizmiko.popcornapp.ui.all.presentation.cast;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.cast.CastModel;

import java.util.List;

public interface CastView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void showCast(final List<CastModel> cast);
}
