package com.blaizmiko.popcornapp.ui.all.presentation.details.cast.base;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.cast.Cast;

import java.util.List;

public interface CastView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setCast(List<Cast> cast);
}
