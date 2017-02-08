package com.blaizmiko.popcornapp.ui.activities.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.activities.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Bind views
    @BindView(R.id.home_drawer_layout)
    protected DrawerLayout mHomeDrawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    //Life cycle
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //Init methods
    @Override
    protected void bindViews() {
        setToolbar(mToolbar);
        setToolbarDisplayHomeButtonEnabled(true);

        final NavigationView navigationMenu = ButterKnife.findById(this, R.id.home_navigation_view);
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                mHomeDrawerLayout,
                mToolbar,
                R.string.navigation_view_open_drawer,
                R.string.navigation_view_close_drawer);

        mHomeDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationMenu.setNavigationItemSelectedListener(this);
    }

    //Listeners
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        mHomeDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
