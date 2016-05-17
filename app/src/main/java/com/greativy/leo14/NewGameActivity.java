package com.greativy.leo14;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Date;

public class NewGameActivity extends AppCompatActivity {
    private Button Button_Submit, Button_Cancel;
    private EditText GameTitle, Player1, Player2, Player3, Player4;
    private GameListItem gameListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        findView();
        onSubmit();
        onCancel();
        Intent intent  = getIntent();
        String action = intent.getAction();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(action.equals("com.greativy.leo14.EDIT_ITEM")) {
            gameListItem = (GameListItem) intent.getExtras().getSerializable("com.greativy.leo14.GameListItem");
            GameTitle.setText(gameListItem.getGameTitle());
            Player1.setText(gameListItem.getPlayer1());
            Player2.setText(gameListItem.getPlayer2());
            Player3.setText(gameListItem.getPlayer3());
            Player4.setText(gameListItem.getPlayer4());
            toolbar.setTitle("EDIT ITEM");
        } else {
            gameListItem = new GameListItem();
        }
        setSupportActionBar(toolbar);


    }

    private void onCancel() {
        Button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder d = new AlertDialog.Builder(NewGameActivity.this);
                d.setTitle(R.string.confirm_cancel)
                        .setMessage(R.string.confirm_cancel_content)
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        NewGameActivity.this.finish();
                                    }
                                });
                d.setNegativeButton(android.R.string.no, null);
                d.show();
            }
        });
    }

    public void onSubmit() {
        Button_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation(v)) {
                    //TODO add sharedpreference to save the player names
                    Intent result  = getIntent();
                    String action = result.getAction();
                    gameListItem.setGameTitle(GameTitle.getText().toString());
                    gameListItem.setPlayer1(Player1.getText().toString());
                    gameListItem.setPlayer2(Player2.getText().toString());
                    gameListItem.setPlayer3(Player3.getText().toString());
                    gameListItem.setPlayer4(Player4.getText().toString());
                    gameListItem.setPlayer1FinalScore(0);
                    gameListItem.setPlayer2FinalScore(0);
                    gameListItem.setPlayer3FinalScore(0);
                    gameListItem.setPlayer4FinalScore(0);


                    if(getIntent().getAction().equals("com.greativy.leo14.ADD_ITEM")) {
                        gameListItem.setCreateTime(new Date().getTime());
                        gameListItem.setModTime(new Date().getTime());


                    } else {
                        gameListItem.setModTime(new Date().getTime());
                    }
                    result.putExtra("com.greativy.leo14.GameListItem", gameListItem);
                    setResult(Activity.RESULT_OK, result);
                    finish();

                }
            }
        });

    }


    private Boolean validation(View v) {
        EditText[] stg = {GameTitle, Player1, Player2};
        EditText[] stg2 = {GameTitle, Player1, Player2, Player3, Player4};
        boolean BL_error = true;

        for (EditText t : stg) {
            TextInputLayout parent = (TextInputLayout) t.getParent();
            if (t.getText().toString().isEmpty()) {
                CharSequence hint = parent.getHint();
                Snackbar.make(v, hint + " is Empty", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                parent.setError(hint + " " + getString(R.string.err_msg_name));
                BL_error = false;
            } else {
                parent.setError(null);
            }
        }

        /**
        for (EditText t: stg2) {
            TextInputLayout parent = (TextInputLayout) t.getParent();
            if (!t.getText().toString().isEmpty() && t.getText().toString().length() >= 20) {
                CharSequence hint = parent.getHint();
                Snackbar.make(v, hint + " must be less than 20 chars ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                parent.setError(hint + " " + getString(R.string.err_maxlength));
                BL_error = false;
            } else {
                parent.setError(null);
            }

        }*/


        return BL_error;

    }


    public void findView() {
        Button_Submit = (Button) findViewById(R.id.button_Submit);
        Button_Cancel = (Button) findViewById(R.id.button_Cancel);
        GameTitle = (EditText) findViewById(R.id.editText_GameTitle);
        Player1 = (EditText) findViewById(R.id.editText_Player1);
        Player2 = (EditText) findViewById(R.id.editText_Player2);
        Player3 = (EditText) findViewById(R.id.editText_Player3);
        Player4 = (EditText) findViewById(R.id.editText_Player4);


    }

    public void toolbar1() {

    }
}
