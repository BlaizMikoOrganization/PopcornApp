package com.blaizmiko.popcornapp.ui.gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;

import butterknife.BindView;

public class GalleryActivity extends BaseMvpActivity implements GalleryView, ViewPager.OnPageChangeListener {

    @InjectPresenter
    GalleryPresenter galleryPresenter;

    //Binding
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.text_view_gallery_page_count)
    protected TextView pageCountTextView;
    @BindView(R.id.viewpager_gallery)
    protected ViewPager viewPager;
    @BindView(R.id.text_view_gallery_cinema_name)
    protected TextView cinemaNameTextView;
    @BindView(R.id.text_view_gallery_release_date)
    protected TextView releaseDateTextView;

    private GalleryAdapter galleryAdapter;

    //Lifecycle
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        galleryPresenter.initGalleryView(getIntent());
    }

    //Binding
    @Override
    protected void bindViews() {
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);
        setToolbarTitleEnabled(false);

        initGalleryAdapter();
    }

    private void initGalleryAdapter() {
        galleryAdapter = new GalleryAdapter(getSupportFragmentManager());

        viewPager.setAdapter(galleryAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.spacing_screen));
    }

    //Gallery presenter
    @Override
    public void setCinemaName(@NonNull final String cinemaName) {
        cinemaNameTextView.setText(cinemaName);
    }

    @Override
    public void setReleaseDate(@NonNull final String releaseDate) {
        releaseDateTextView.setText(releaseDate);
    }

    @Override
    public void setPageCount(final int currentPosition, final int totalAmount) {
        pageCountTextView.setText(String.format(getString(R.string.gallery_item_of_title), currentPosition, totalAmount));
    }

    @Override
    public void setGallery(final String[] imageUrls, final int currentPosition) {
        galleryAdapter.update(imageUrls);
        viewPager.setCurrentItem(currentPosition);
    }

    //Listeners
    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(final int position) {
        galleryPresenter.setPageCount(position);
    }

    @Override
    public void onPageScrollStateChanged(final int state) {
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
