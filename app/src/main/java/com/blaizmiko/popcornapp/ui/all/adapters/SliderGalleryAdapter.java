package com.blaizmiko.popcornapp.ui.all.adapters;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.fragments.GalleryItemFragment;

public class SliderGalleryAdapter extends FragmentStatePagerAdapter{
    private String [] imageUrls;

    public SliderGalleryAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.Extras.BACKDROP_URL, imageUrls[position]);
        GalleryItemFragment galleryFragment = GalleryItemFragment.newInstance();
        galleryFragment.setArguments(bundle);
        return galleryFragment;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    public void update(String [] imageUrls) {
        this.imageUrls = imageUrls;
    }
}
