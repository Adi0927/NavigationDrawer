package com.example.android.navigationdrawerexample;


import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.navigationdrawerexample.fragments.AboutFragment;
import com.example.android.navigationdrawerexample.fragments.ChapterFragment;
import com.example.android.navigationdrawerexample.fragments.HelpFragment;
import com.example.android.navigationdrawerexample.fragments.HomeFragment;
import com.example.android.navigationdrawerexample.fragments.ImpFragment;
import com.example.android.navigationdrawerexample.fragments.LogoutFragment;
import com.example.android.navigationdrawerexample.fragments.SavedFragment;
import com.example.android.navigationdrawerexample.fragments.SettingsFragment;
import com.example.android.navigationdrawerexample.fragments.YearFragment;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView nvDrawer;
    MenuItem mPreviousMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.home);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setCheckable(true);
                        menuItem.setChecked(true);
                        if (mPreviousMenuItem != null) {
                            mPreviousMenuItem.setChecked(false);
                        }
                        mPreviousMenuItem = menuItem;
                        selectDrawerItem(mPreviousMenuItem);
                        return true;
                    }
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;

        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.about:
                fragmentClass = AboutFragment.class;
                break;
            case R.id.home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.chapter:
                fragmentClass = ChapterFragment.class;
                break;
            case R.id.year:
                fragmentClass = YearFragment.class;
                break;
            case R.id.saved:
                fragmentClass = SavedFragment.class;
                break;
            case R.id.imp:
                fragmentClass = ImpFragment.class;
                break;
            case R.id.settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.help:
                fragmentClass = HelpFragment.class;
                break;
            case R.id.logout:
                fragmentClass = LogoutFragment.class;
                break;
            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        Log.i("fuck", menuItem.toString()+menuItem.isChecked()+"");
//        menuItem.setChecked(true);
//        menuItem.setChecked(true);
//        Log.i("fuck", menuItem.toString() + menuItem.isChecked() + "");
        mToolbar.setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void everyTime() {
        Class fragmentClass = HomeFragment.class;
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();
    }
}