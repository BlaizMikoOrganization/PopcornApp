package com.blaizmiko.popcornapp.ui.all.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.adapters.SliderGalleryAdapter;

import butterknife.BindView;

public class SliderGalleryActivity extends BaseMvpActivity implements ViewPager.OnPageChangeListener{
    final int INDEXATION_FROM_ZERO_TO_ONE = 1;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.text_view_slider_gallery_current_item)
    protected TextView currentItemTextView;
    @BindView(R.id.text_view_slider_gallery_total_amount)
    protected TextView totalAmountTextView;
    @BindView(R.id.viewpager_gallery)
    protected ViewPager viewPager;
    @BindView(R.id.text_view_gallery_item_cinema_name)
    protected TextView cinemaNameTextView;
    @BindView(R.id.text_view_gallery_item_release_date)
    protected TextView releaseDateTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_gallery);
    }

    @Override
    protected void bindViews() {
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);
        setToolbarTitle("");

        String [] imageUrls = getIntent().getStringArrayExtra(Constants.Extras.URLS_ARRAY);
        totalAmountTextView.setText(Integer.toString(imageUrls.length));

        final int DEFAULT_POSITION = 0;
        int currentPosition = getIntent().getIntExtra(Constants.Extras.POSITION, DEFAULT_POSITION);
        currentItemTextView.setText(Integer.toString(currentPosition + INDEXATION_FROM_ZERO_TO_ONE));

        initViewPager(imageUrls, currentPosition);

        cinemaNameTextView.setText(getIntent().getStringExtra(Constants.Extras.TITLE));
        releaseDateTextView.setText(getIntent().getStringExtra(Constants.Extras.RELEASE_DATE));
    }

    private void initViewPager(String [] imageUrls, int currentPosition) {
        SliderGalleryAdapter adapter = new SliderGalleryAdapter(getSupportFragmentManager());
        adapter.update(imageUrls);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.spacing_screen));
        viewPager.setCurrentItem(currentPosition);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
        currentItemTextView.setText(Integer.toString(position + INDEXATION_FROM_ZERO_TO_ONE));
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
