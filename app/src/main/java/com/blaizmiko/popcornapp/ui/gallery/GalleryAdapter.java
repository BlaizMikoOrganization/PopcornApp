package com.blaizmiko.popcornapp.ui.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.blaizmiko.popcornapp.application.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GalleryAdapter extends FragmentStatePagerAdapter {

    private List<String> imageUrls;

    GalleryAdapter(final FragmentManager fragmentManager) {
        super(fragmentManager);
        imageUrls = new ArrayList<>();
    }

    @Override
    public Fragment getItem(final int position) {
        final Bundle bundle = new Bundle();
        bundle.putString(Constants.Extras.BACKDROP_URL, imageUrls.get(position));

        final GalleryItemFragment galleryFragment = GalleryItemFragment.newInstance();
        galleryFragment.setArguments(bundle);

        return galleryFragment;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    public void update(final String[] imageUrls) {
        this.imageUrls = new ArrayList<>(Arrays.asList(imageUrls));
        notifyDataSetChanged();
    }
}
