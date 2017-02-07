package com.blaizmiko.popcornapp.ui.activities.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.activities.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.home_drawer_layout) protected DrawerLayout mHomeDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        NavigationView navigationMenu = ButterKnife.findById(this, R.id.home_navigation_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mHomeDrawerLayout, toolbar, R.string.test_string, R.string.test_string) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mHomeDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationMenu.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mHomeDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
