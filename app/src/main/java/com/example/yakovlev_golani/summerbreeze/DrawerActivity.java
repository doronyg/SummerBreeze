package com.example.yakovlev_golani.summerbreeze;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yakovlev_golani.summerbreeze.fragments.ChallengeFourFragment;
import com.example.yakovlev_golani.summerbreeze.fragments.ChallengeOneFragment;
import com.example.yakovlev_golani.summerbreeze.fragments.ChallengeSevenFragment;
import com.example.yakovlev_golani.summerbreeze.fragments.ChallengeSixFragment;
import com.example.yakovlev_golani.summerbreeze.fragments.ChallengeThreeFragment;
import com.example.yakovlev_golani.summerbreeze.fragments.ChallengeTwoFragment;
import com.example.yakovlev_golani.summerbreeze.fragments.HistoricalDataFragment;


public class DrawerActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String mChallangesStrings[];

    public static String getChallengeString(int i) {
        return "Challenge " + (i + 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLoadingSpinnerView();

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        mChallangesStrings = new String[7];
        for (int i = 0; i < 7; i++) {
            mChallangesStrings[i] = getChallengeString(i);
        }

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mChallangesStrings));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment;

        switch (position){
            case 0:
                fragment = new ChallengeOneFragment();
                break;
            case 1:
                fragment = new ChallengeTwoFragment();
                break;
            case 2:
                fragment = new ChallengeThreeFragment();
                break;
            case 3:
                fragment = new ChallengeFourFragment();
                break;
            case 4:
                fragment = new HistoricalDataFragment();
                break;
            case 5:
                fragment = new ChallengeSixFragment();
                break;
            case 6:
                fragment = new ChallengeSevenFragment();
                break;
            default:
                fragment = new EmptyChallengeFragment();
                break;
        }

        Bundle args = new Bundle();
        args.putInt(EmptyChallengeFragment.CHALLENGE_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.forecast_content, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(getChallengeString(position));
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class EmptyChallengeFragment extends Fragment {
        public static final String CHALLENGE_NUMBER = "challengeNumber";

        public EmptyChallengeFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.challenge, container, false);
            int i = getArguments().getInt(CHALLENGE_NUMBER);

            ((TextView) rootView.findViewById(R.id.challenge_name)).setText(getChallengeString(i));
            getActivity().setTitle(getChallengeString(i));
            return rootView;
        }
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}