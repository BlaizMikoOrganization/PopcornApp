package com.blaizmiko.popcornapp.ui.activities.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.activities.base.BaseActivity;
import com.blaizmiko.popcornapp.ui.fragments.actors.ActorsFragment;
import com.blaizmiko.popcornapp.ui.fragments.movies.MoviesFragment;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Bind views
    @BindView(R.id.activity_home_drawer_layout)
    protected DrawerLayout mDrawerLayout;

    @BindView(R.id.activity_home_navigation_view)
    protected NavigationView mNavigationView;

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

        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_view_open_drawer,
                R.string.navigation_view_close_drawer);

        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        onNavigationItemSelected(mNavigationView.getMenu().getItem(2));
    }

    //Listeners
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        selectNavigationMenuItem(item);

        return true;
    }

    private void selectNavigationMenuItem(@NonNull final MenuItem menuItem) {
        final Fragment fragment;

        switch(menuItem.getItemId()) {
            case R.id.navigation_menu_movies_id:
                fragment = MoviesFragment.newInstance();
                break;
            case R.id.navigation_menu_tv_id:
                fragment = MoviesFragment.newInstance();
                break;
            case R.id.navigation_menu_actors_id:
                fragment = ActorsFragment.newInstance();
                break;
            case R.id.navigation_menu_about_id:
                fragment = MoviesFragment.newInstance();
                break;
            default:
                fragment = MoviesFragment.newInstance();
        }

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_home_container_layout, fragment).commitNow();

        mNavigationView.setCheckedItem(menuItem.getItemId());

        setToolbarTitle(menuItem.getTitle());

        mDrawerLayout.closeDrawers();
    }
}
