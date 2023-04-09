package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPref;
    private String sharedPrefFile = "com.example.flashcardapp";
    private int numCorrect;
    public static final String EXTRA_CARDNAME = "com.example.flashcardapp.extra.CARDNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> characterList = new ArrayList<String>();
        characterList.add("あ");
        characterList.add("い");
        characterList.add("う");
        characterList.add("え");
        characterList.add("お");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, characterList);
        ListView listView = (ListView) findViewById(R.id.characterList);
        listView.setAdapter(adapter);

        TextView textCorrect = (TextView) findViewById(R.id.textCorrect);
        mPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        numCorrect = mPref.getInt("CORRECT", numCorrect);
        textCorrect.setText(String.format("# CORRECT: %s", numCorrect));
    }
    public void onClick(View view) {
        ArrayList<String> answerList = new ArrayList<String>();
        TextView clickedCard = (TextView) view;
        String cardName = clickedCard.getText().toString();
        Log.d("LOG_TAG", cardName);
        answerList.add("a");
        answerList.add("i");
        answerList.add("u");
        answerList.add("e");
        answerList.add("o");
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_CARDNAME, cardName);
        intent.putExtra("answers", answerList);
        startActivity(intent);
    }

    public void onResume() {
        super.onResume();
        TextView textCorrect = (TextView) findViewById(R.id.textCorrect);
        mPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        numCorrect = mPref.getInt("CORRECT", numCorrect);
        textCorrect.setText(String.format("# CORRECT: %s", numCorrect));
    }
}