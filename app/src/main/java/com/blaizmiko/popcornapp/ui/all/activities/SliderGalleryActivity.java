package com.blaizmiko.popcornapp.ui.all.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.adapters.SliderGalleryAdapter;

import butterknife.BindView;

public class SliderGalleryActivity extends BaseMvpActivity implements ViewPager.OnPageChangeListener{
    private int currentPosition;
    @BindView(R.id.viewpager_gallery)
    protected ViewPager viewPager;
    @BindView(R.id.text_view_gallery_item_cinema_name)
    protected TextView cinemaNameTextView;
    @BindView(R.id.text_view_gallery_item_release_date)
    protected TextView releaseDateTextView;
    @BindView(R.id.text_view_gallery_item_current_position)
    protected TextView currentPositionTextView;
    @BindView(R.id.text_view_gallery_item_total_items)
    protected TextView totalItemsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_gallery);
    }



    @Override
    protected void bindViews() {
        String [] imageUrls = getIntent().getStringArrayExtra(Constants.Extras.URL_ARRAY);
        final int DEFAULT_POSITION = 0;
        currentPosition = getIntent().getIntExtra(Constants.Extras.POSITION, DEFAULT_POSITION);
        SliderGalleryAdapter adapter = new SliderGalleryAdapter(getSupportFragmentManager());
        adapter.update(imageUrls);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(currentPosition);

        cinemaNameTextView.setText(getIntent().getStringExtra(Constants.Extras.TITLE));
        releaseDateTextView.setText(getIntent().getStringExtra(Constants.Extras.RELEASE_DATE));
        totalItemsTextView.setText(Integer.toString(imageUrls.length));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
        currentPositionTextView.setText(Integer.toString(position + 1));
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
