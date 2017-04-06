package com.blaizmiko.popcornapp.ui.all.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabsAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList;
    private final List<String> titleList;

    public TabsAdapter(final FragmentManager manager) {
        super(manager);
        fragmentList = new ArrayList<>();
        titleList =  new ArrayList<>();
    }

    @Override
    public Fragment getItem(final int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(final Fragment fragment, final String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return titleList.get(position);
    }
}
