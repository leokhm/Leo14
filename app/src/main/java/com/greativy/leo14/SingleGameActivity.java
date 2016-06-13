package com.greativy.leo14;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import android.os.Handler;

public class SingleGameActivity extends AppCompatActivity implements StatFragment.OnFragmentInteractionListener, RoundFragment.OnItemClickListener, NewRoundDialogFragment.OnItemSubmitListener {


    private mFragmentPagerAdapter mFragmentPagerAdapter;
    private ViewPager mViewPager;
    private SingleGameDAO mSingleGameDAO;
    private SingleGameItem mSingleGameItem;
    private android.support.design.widget.TabLayout mTabs;
    private String KEY_GAMELISTITEM = "GameListItem";
    private String KEY_SINGLEGAMEITEM = "SingleGameItem";
    private Bundle bundle_newRound;

    public void onListFragmentInteraction(List<SingleGameItem> items) {
        Log.i("SingleGameAcitivty", "onListFragmentInteraction");

        //you can leave it empty
    }

    public void onDialogFragmentInteraction(SingleGameItem mSingleGameItem) {
        if(mSingleGameItem != null) {
            mSingleGameDAO = new SingleGameDAO(getApplicationContext());
            Log.i("SingleGameAcitivty", "onDialogFragmentInteraction");
            final RecyclerView mRecyclerView;
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_singleGame);
            mSingleGameDAO.insert(mSingleGameItem);
            // TODO fix recyclerview refresh animation
            mRecyclerView.getAdapter().notifyItemInserted(1);
            mRecyclerView.scrollToPosition(1);
            mFragmentPagerAdapter.notifyDataSetChanged();
            mViewPager.setAdapter(mFragmentPagerAdapter);


        }
        //mViewPager.setAdapter(mFragmentPagerAdapter);

        //Toast.makeText(this, mSingleGameItem.getPlayer1RoundScore(), Toast.LENGTH_LONG).show();
    }
    //you can leave it empty


    public void onFragmentInteraction() {
        //you can leave it empty
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);
        toolbar();

        /** get the Intent and pass to Bundle*/
        Intent intent = getIntent();
        GameListItem mGameListItem = (GameListItem) intent.getExtras().getSerializable("GameListItem");
        bundle_newRound = new Bundle();
        bundle_newRound.putSerializable(KEY_GAMELISTITEM, mGameListItem);
        /** setup toolbar title */
        setTitle(getString(R.string.app_name) + " " + mGameListItem.getGameTitle());
        /** test the correct GameListItem Id by toast*/
        Toast.makeText(getApplicationContext(), String.valueOf(mGameListItem.getGameTitle()) + " - ID " + String.valueOf(mGameListItem.getId()), Toast.LENGTH_LONG).show();
        /** setup viewpager and pass the FragmentPagerAdapter into it, and setup the tabs */
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabs = (TabLayout) findViewById(R.id.tabs);
        mFragmentPagerAdapter = new mFragmentPagerAdapter(getApplicationContext(), getSupportFragmentManager(), mGameListItem);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabs.setupWithViewPager(mViewPager);
        /** setup floating action button with onClick method */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NewRoundDialogFragment editNameDialog = new NewRoundDialogFragment();
                editNameDialog.setArguments(bundle_newRound);
                editNameDialog.show(getSupportFragmentManager(), "EditNameDialog");

            }
        });

    }


    public void toolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_game, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab_a, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));


            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class mFragmentPagerAdapter extends FragmentPagerAdapter {
        public GameListItem mGameListItem;
        public Context context;
        public SingleGameItem mSingleGameItem;

        public mFragmentPagerAdapter(Context context, FragmentManager fm, GameListItem mGameListItem) {
            super(fm);
            this.context = context;
            this.mGameListItem = mGameListItem;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RoundFragment.newInstance(mGameListItem);

                case 1:

                    return StatFragment.newInstance(mGameListItem);

                case 2:
                    return PlaceholderFragment.newInstance(position + 1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return context.getString(R.string.round);
                case 1:
                    return context.getString(R.string.stat);
                case 2:
                    return context.getString(R.string.more);
            }
            return null;
        }






    }
}