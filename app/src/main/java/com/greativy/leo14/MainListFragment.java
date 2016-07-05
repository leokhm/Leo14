package com.greativy.leo14;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainListFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private GameListRecyclerAdapter gameListRecyclerAdapter;
    private RecyclerView recyclerView;
    private GameListDAO gameListDAO;
    private List<GameListItem> items;
    private GameListAdapter gameListAdapter;
    private SwipeRefreshLayout refreshLayout;
    private final int splashTime = 1000;



    public MainListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainListFragment newInstance(String param1, String param2) {
        MainListFragment fragment = new MainListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);
        swipeRefresh(view);

        Log.i("onCreateView", "test recyclerview");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_main);
        // 创建线性布局管理器（默认是垂直方向）
        final LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        // 为RecyclerView指定布局管理对象
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 创建Adapter
        gameListDAO = new GameListDAO(getActivity().getApplicationContext());
        if (gameListDAO.getCount() == 0) {
            gameListDAO.sample();
        }
        items = gameListDAO.getAll();
        gameListRecyclerAdapter = new GameListRecyclerAdapter(items, new GameListRecyclerAdapter.OnItemClickListener() {
            @Override
            public void clickOnView(View v, int position) {
                GameListItem item = items.get(position);

                //Snackbar.make(v, "test", Snackbar.LENGTH_LONG).show();

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("GameListItem", item);
                intent.putExtras(bundle);
                intent.putExtra("position", position);
                //intent.setClass(MainListActivity.this, SingleGameActivity.class);
                startActivity(intent);


            }
        });
        // 填充Adapter
        //disable divider
        //RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.LIST_VERTICAL);
        //recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(gameListRecyclerAdapter);
        /*gameListRecyclerAdapter.SetOnItemClickListener(new GameListRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //do something with position
            }
        });
        */// Inflate the layout for this fragment

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void swipeRefresh(View view) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler h = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);

                    }
                };
                h.postDelayed(r, splashTime);
            }
        });
    }
}
