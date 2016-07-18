package com.greativy.leo14;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by leokh on 7/15/2016.
 */

public class GameListSwipeHelper extends ItemTouchHelper.SimpleCallback {



    GameListRecyclerAdapter gameListRecyclerAdapter;
    Context c;
    RecyclerView recyclerView;

    public GameListSwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public GameListSwipeHelper(GameListRecyclerAdapter gameListRecyclerAdapter, Context c, RecyclerView recyclerView) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.gameListRecyclerAdapter = gameListRecyclerAdapter;
        this.c = c;
        this.recyclerView = recyclerView;

    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
        gameListRecyclerAdapter.onItemDismiss(viewHolder.getAdapterPosition(),c, recyclerView);
        /* TODO show dialog to confirm removal
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainListActivity.this);
        alertDialogBuilder.setMessage("are you sure wanna delete?");
        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameListRecyclerAdapter.onItemDismiss(viewHolder.getAdapterPosition(),c, recyclerView);

            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        */




    }
}
