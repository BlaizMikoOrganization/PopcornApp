package com.blaizmiko.ui_demo.base.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blaizmiko.ui_demo.R;
import com.blaizmiko.ui_demo.base.adapters.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        final RecyclerView recyclerView = (RecyclerView) findViewById(android.R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        try {
            final ActivityInfo[] list = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES).activities;

            final List<String> titles = new ArrayList<>();

            for (final ActivityInfo activityInfo : list) {
                final String name = activityInfo.name;

                if (name.equals(getClass().getCanonicalName())) {
                    continue;
                }

                final String activity = name.substring(name.lastIndexOf(".") + 1).replace("Activity", "");
                titles.add(activity);
            }

            final ListAdapter adapter = new ListAdapter(titles);

            adapter.setItemClickListener(new ListAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(final int position) {
                    try {
                        startActivity(new Intent(MainActivity.this, Class.forName(list[position].name)));
                    } catch (final ClassNotFoundException e) {
                        throw new IllegalStateException(e);
                    }
                }
            });

            recyclerView.setAdapter(adapter);
        } catch (final PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("application need to be installed");
        }

    }
}
