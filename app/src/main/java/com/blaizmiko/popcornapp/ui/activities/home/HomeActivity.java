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
    @BindView(R.id.home_drawerLayout) protected DrawerLayout mHomeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        DrawerLayout homeLayout = ButterKnife.findById(this, R.id.home_drawerLayout);
        NavigationView navigationMenu = ButterKnife.findById(this, R.id.home_navigation_menu_nav_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, homeLayout, toolbar, R.string.test_string, R.string.test_string) {
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

        homeLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationMenu.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mHomeLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
