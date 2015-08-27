package com.oddsoft.pickashop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.oddsoft.pickashop.Fragments.ContactFragment;
import com.oddsoft.pickashop.Fragments.FaqFragment;
import com.oddsoft.pickashop.Fragments.HomeFragment;
import com.oddsoft.pickashop.Global.Constants;

public class HomeActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
       onSectionAttached(position + 1);
    }

    public void onSectionAttached(int number) {
        FragmentManager frMng = getSupportFragmentManager();
        Fragment fr;
        switch (number) {
            case 1:
                HomeFragment homeFragment;
                fr = frMng.findFragmentByTag(Constants.HOME_FRAGMENT_TAG);
                if (fr != null) {
                    homeFragment = (HomeFragment) fr;
                } else {
                    homeFragment = new HomeFragment();
                }
                setFragment(homeFragment, Constants.HOME_FRAGMENT_TAG);
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = "OFFERS";
                ContactFragment contactFragment1;
                fr = frMng.findFragmentByTag(Constants.CONTACT_FRAGMENT_TAG);
                if (fr != null) {
                    contactFragment1 = (ContactFragment) fr;
                } else {
                    contactFragment1 = new ContactFragment();
                }
                setFragment(contactFragment1, Constants.CONTACT_FRAGMENT_TAG);
                break;
            case 3:
                mTitle = getString(R.string.title_section2);
                ContactFragment contactFragment;
                fr = frMng.findFragmentByTag(Constants.CONTACT_FRAGMENT_TAG);
                if (fr != null) {
                    contactFragment = (ContactFragment) fr;
                } else {
                    contactFragment = new ContactFragment();
                }
                setFragment(contactFragment, Constants.CONTACT_FRAGMENT_TAG);
                break;
            case 4:
                mTitle = getString(R.string.title_section3);
                FaqFragment faqFragment;
                fr = frMng.findFragmentByTag(Constants.FAQ_FRAGMENT_TAG);
                if (fr != null) {
                    faqFragment = (FaqFragment) fr;
                } else {
                    faqFragment = new FaqFragment();
                }
                setFragment(faqFragment,Constants.FAQ_FRAGMENT_TAG);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment fr, String tag) {
        FragmentManager frMng = getSupportFragmentManager();
        FragmentTransaction ft = frMng.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.container, fr, tag);
        ft.commit();
    }

    public void setFragmentOthers(Fragment fr, String tag) {
        FragmentManager frMng = getSupportFragmentManager();
        FragmentTransaction ft = frMng.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.add(R.id.container, fr, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    private void initHomeFragment() {
        HomeFragment homeFragment;
        FragmentManager frMng = getSupportFragmentManager();
        Fragment fr = frMng.findFragmentByTag(Constants.HOME_FRAGMENT_TAG);
        if (fr != null) {
            homeFragment = (HomeFragment) fr;
        } else {
            homeFragment = new HomeFragment();
        }
        FragmentTransaction ft = frMng.beginTransaction();
        ft.add(R.id.container, homeFragment, Constants.HOME_FRAGMENT_TAG);
        ft.commit();
    }

}
