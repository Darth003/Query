package com.devdefiance.android.query;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.devdefiance.android.query.fragments.ExploreFragment;
import com.devdefiance.android.query.fragments.HomeFragment;
import com.devdefiance.android.query.fragments.ProfilesFragment;
import com.devdefiance.android.query.fragments.SourcesFragment;
import com.devdefiance.android.query.utils.BasicUtils;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static int NAV_POSITION;
    private String LOG_TAG = "com.devdefiance.android.query.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This block checks if the device can use the
        //windows no limits flag.
        if (BasicUtils.canUseWindowsNoLimit()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        // Handle views
        setContentView(R.layout.activity_main);
        initViews();

        if (savedInstanceState != null) {
            return;
        }

        // Add the home fragment
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_container, homeFragment)
                .commit();
    }

    private void initViews() {
        // Initialize the views here.
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.empty_text);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.mnav_home_item);
        NAV_POSITION = 0;
        navigationView.setNavigationItemSelectedListener(this);
    }

    private <T extends Fragment> void switchFragments(T newFragment) {
        // Close Input Method
        // can throw NullPointerException
        try {
            BasicUtils.closeInputMethod(this, getCurrentFocus());
        } catch (NullPointerException npe) {
            Log.e(this.LOG_TAG, "cannot close input method");
        }
        // Switch to a different fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, newFragment)
                .commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.mnav_home_item:
                if (NAV_POSITION != 0) {
                    switchFragments(new HomeFragment());
                }
                NAV_POSITION = 0;

                break;

            case R.id.mnav_explore_item:
                if (NAV_POSITION != 1) {
                    switchFragments(new ExploreFragment());
                }
                NAV_POSITION = 1;

                break;

            case R.id.mnav_profiles_item:
                if (NAV_POSITION != 2) {
                    switchFragments(new ProfilesFragment());
                }
                NAV_POSITION = 2;

                break;

            case R.id.mnav_sources_item:
                if (NAV_POSITION != 3) {
                    switchFragments(new SourcesFragment());
                }
                NAV_POSITION = 3;

                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
