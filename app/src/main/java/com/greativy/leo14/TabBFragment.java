package com.greativy.leo14;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnItemClickListener}
 * interface.
 */
public class TabBFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnItemClickListener mListener;
    private List<SingleGameItem> items;
    private SingleGameDAO singleGameDAO;
    private RoundListRecyclerViewAdapter roundListRecyclerViewAdapter;

    private static final String ACTIVITY_TAG="LogDemo";



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TabBFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TabBFragment newInstance() {
        TabBFragment fragment = new TabBFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_b_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView =(RecyclerView) view.findViewById(R.id.recyclerview_sgame);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            String player1 = this.getArguments().getString("player1");
            String player2 = this.getArguments().getString("player2");
            String player3 = this.getArguments().getString("player3");
            String player4 = this.getArguments().getString("player4");
            Toast.makeText(getActivity(), player1, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), player2, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), player3, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), player4, Toast.LENGTH_LONG).show();


            singleGameDAO = new SingleGameDAO(getActivity().getApplicationContext());
            if (singleGameDAO.getCount() == 0) {
                singleGameDAO.sample();
            }
            items = singleGameDAO.getAllByGameId(1);


            roundListRecyclerViewAdapter = new RoundListRecyclerViewAdapter(items, new RoundListRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void clickOnView(View v, int position) {
                    SingleGameItem item = items.get(position);

                    Snackbar.make(v, "test", Snackbar.LENGTH_LONG).show();





                }
            });




            recyclerView.setAdapter(roundListRecyclerViewAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemClickListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnItemClickListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(SingleGameItem item);
    }
}
