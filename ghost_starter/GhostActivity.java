package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private String wordFragment ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
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
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        TextView text = (TextView) findViewById(R.id.ghostText);
        String usertextvalue = text.getText().toString();
        String computertextvalue = null;
        int usertextvaluelength = usertextvalue.length();
        if( dictionary.isWord(usertextvalue)){
                        label.setText("The computer has won");
        }
        else {
            String nextword = dictionary.getAnyWordStartingWith(usertextvalue);
             if( nextword == null){
                    label.setText("Computer won");
             }
            else{
                 usertextvalue = usertextvalue + nextword.substring(usertextvaluelength,usertextvaluelength+1);
                 text.setText(usertextvalue);
             }
        }


        // Do computer turn stuff then make it the user's turn again
//        userTurn = true;
  //      label.setText(USER_TURN);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        char i = (char)event.getUnicodeChar();
        TextView text = (TextView) findViewById(R.id.ghostText);
        String usertext = text.getText().toString();
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if((i >= 'a' && i <= 'z')) {
            Log.d("test", String.valueOf(i));

            usertext += i;
            text.setText(usertext);
            label.setText(COMPUTER_TURN);
            computerTurn();

        }

        if (i >= 'A' && i <= 'Z'){

            i = (char)(i -'A' + 'a');
            Log.d("test", String.valueOf(i));
            wordFragment += i;
            text.setText(usertext);
            userTurn = false;
            label.setText(COMPUTER_TURN);
            computerTurn();
        }




        Log.d("keyupmessage","test");
        return super.onKeyUp(keyCode, event);
    }
}
