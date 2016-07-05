package com.greativy.leo14;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.List;

public class MainListActivity extends AppCompatActivity implements MainListFragment.OnFragmentInteractionListener {
    //delete declaration with-tempgamelist
    //private ArrayAdapter<String> arrayListAdapter;
    //private ArrayList<String> listData = new ArrayList<>();
    private ListView item_list;
    private GameListDAO gameListDAO;
    private List<GameListItem> items;
    private GameListAdapter gameListAdapter;

    private GameListRecyclerAdapter gameListRecyclerAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        toolbar1();
        //tempGamelist();
        //Gamelist();
        fab();
        //OnItemLongClick();
        //OnItemClick();
        //swipeRefresh();

        Fragment mainListFragment = new MainListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.MainActivity, mainListFragment,"first");
        ft.commit();


    }



    public void OnItemClick() {
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameListItem item = gameListAdapter.getItem(position);

                Intent intent = new Intent();
                intent.putExtra("position", position);
                intent.putExtra("com.greativy.leo14.GamelistItem", item);
                intent.setClass(MainListActivity.this, SingleGameActivity.class);
                startActivity(intent);
            }
        };
        item_list.setOnItemClickListener(itemClickListener);
    }

    public void OnItemLongClick() {
        AdapterView.OnItemLongClickListener itemLongListener = new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                GameListItem item = gameListAdapter.getItem(position);
                Intent intent = new Intent("com.greativy.leo14.EDIT_ITEM");
                intent.putExtra("position", position);
                intent.putExtra("com.greativy.leo14.GameListItem", item);
                startActivityForResult(intent, 1);
                return false;
            }

        };

        item_list.setOnItemLongClickListener(itemLongListener);

    }

    /* depreciated for custom arrayadapter
     * private void tempGamelist() {
     * //temporary adapter
     * listData.add("test1");
     * listData.add("test2");
     * listData.add("test3");
     * arrayListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData);
     * item_list = (ListView) findViewById(R.id.listView_itemList);
     * item_list.setAdapter(arrayListAdapter);
     * }
     **/

    public void Gamelist() {
        gameListDAO = new GameListDAO(getApplicationContext());
        //item_list = (ListView) findViewById(R.id.listView_itemList);
        if (gameListDAO.getCount() == 0) {
            gameListDAO.sample();
        }
        items = gameListDAO.getAll();
        gameListAdapter = new GameListAdapter(this, R.layout.item_game_list, items);
        item_list.setAdapter(gameListAdapter);

    }

    private void toolbar1() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void fab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.greativy.leo14.ADD_ITEM");
                startActivityForResult(intent, 0);

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            GameListItem item = (GameListItem) data.getExtras().getSerializable(
                    "com.greativy.leo14.GameListItem");
            if (requestCode == 0) {
                item.setId(items.size() + 1);
                items.add(0, item);
                //gameListAdapter.notifyDataSetChanged();
                gameListRecyclerAdapter.notifyItemInserted(0);
                recyclerView.scrollToPosition(0);


            } else if (requestCode == 1) {

                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    items.set(position, item);
                    //gameListAdapter.notifyDataSetChanged();
                    gameListRecyclerAdapter.notifyItemInserted(items.size() + 1);
                }


            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

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


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
