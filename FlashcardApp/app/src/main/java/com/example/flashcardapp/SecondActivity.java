package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private SharedPreferences mPref;
    private String sharedPrefFile = "com.example.flashcardapp";
    private int numCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Get the symbol clicked on main activity
        Intent intent = getIntent();
        String cardName = intent.getStringExtra(MainActivity.EXTRA_CARDNAME);
        TextView textView = findViewById(R.id.studyWord);
        textView.setText(cardName);
        //List all buttons into grid
        ArrayList<String> answerList = (ArrayList<String>) intent.getSerializableExtra("answers");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_buttons, answerList);
        GridView gridView = (GridView) findViewById(R.id.buttonList);
        gridView.setAdapter(adapter);
    }

    public void onClick(View view) {
        TextView clickedButton = (TextView) view;
        String buttonName = clickedButton.getText().toString();
        Log.d("BUTTON_TAG", buttonName);
        //Get the symbol name
        Intent intent = getIntent();
        String cardName = intent.getStringExtra(MainActivity.EXTRA_CARDNAME);
        Toast correct = Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT);
        Toast incorrect = Toast.makeText(getApplicationContext(), "INCORRECT", Toast.LENGTH_SHORT);
        mPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        numCorrect = mPref.getInt("CORRECT", numCorrect);
        switch(buttonName) {
            case "a":
                if(cardName.equals("あ")) {
                    correct.show();
                    numCorrect = numCorrect + 1;
                }
                else
                    incorrect.show();
                break;
            case "i":
                if(cardName.equals("い")){
                    correct.show();
                    numCorrect = numCorrect + 1;
                }
                else
                    incorrect.show();
                break;
            case "u":
                if(cardName.equals("う")){
                    correct.show();
                    numCorrect = numCorrect + 1;
                }
                else
                    incorrect.show();
                break;
            case "e":
                if(cardName.equals("え")){
                    correct.show();
                    numCorrect = numCorrect + 1;
                }
                else
                    incorrect.show();
                break;
            case "o":
                if(cardName.equals("お")){
                    correct.show();
                    numCorrect = numCorrect + 1;
                }
                else
                    incorrect.show();
                break;
        }
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPref.edit();
        preferencesEditor.putInt("CORRECT", numCorrect);
        preferencesEditor.apply();
    }
}