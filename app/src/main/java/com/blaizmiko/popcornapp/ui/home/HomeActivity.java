package com.blaizmiko.popcornapp.ui.home;

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
import com.blaizmiko.popcornapp.ui.all.activities.BaseActivity;
import com.blaizmiko.popcornapp.ui.about.AboutAppFragment;
import com.blaizmiko.popcornapp.ui.actors.PopularActorsFragment;
import com.blaizmiko.popcornapp.ui.movies.MoviesFragment;
import com.blaizmiko.popcornapp.ui.tvshows.TvShowsFragment;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Bind views
    @BindView(R.id.activity_home_root_view)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view_activity_home_side_menu)
    protected NavigationView navigationView;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    //Life cycle
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //Init methods
    @Override
    protected void bindViews() {
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);

        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_view_open_drawer,
                R.string.navigation_view_close_drawer);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        onNavigationItemSelected(navigationView.getMenu().getItem(2));
    }

    //Listeners
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
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
                fragment = TvShowsFragment.newInstance();
                break;
            case R.id.navigation_menu_actors_id:
                fragment = PopularActorsFragment.newInstance();
                break;
            case R.id.navigation_menu_about_id:
                fragment = AboutAppFragment.newInstance();
                break;
            default:
                fragment = AboutAppFragment.newInstance();
        }

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout_activity_home_container, fragment).commitNow();

        navigationView.setCheckedItem(menuItem.getItemId());

        setToolbarTitle(menuItem.getTitle());

        drawerLayout.closeDrawers();
    }
}
