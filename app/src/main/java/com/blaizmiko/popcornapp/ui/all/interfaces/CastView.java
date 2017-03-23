package com.blaizmiko.popcornapp.ui.all.interfaces;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.cast.CastModel;

import java.util.List;

public interface CastView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setCast(List<CastModel> cast);
}
