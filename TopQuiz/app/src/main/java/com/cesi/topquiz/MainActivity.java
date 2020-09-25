package com.cesi.topquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cesi.model.User;

public class MainActivity extends AppCompatActivity {

    private Button mPlaybutton;
    private EditText mEditText;
    public final int ACTIVITY_CODE = 109;
    private User user;
    private SharedPreferences preferences;
    public final String Firstname = "firstname";
    public final String SCORE = "score";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlaybutton = findViewById(R.id.btn_start);
        mPlaybutton.setEnabled(false);
        mEditText = findViewById(R.id.submit_name);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlaybutton.setEnabled(!s.toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // regarder le firstname -
        preferences = getPreferences(MODE_PRIVATE);
        String firstName = preferences.getString(Firstname, null);
        if (Firstname != null) {
            mEditText.setText(firstName);
            int score = preferences.getInt(SCORE, -1);
            if (score >= 0) {
                TextView text =  findViewById(R.id.txt_welcome);
                text.setText("De retour " + firstName + "! Dernier score : " + score);
            }
        }
    }

    public void onPlay(View view) {
        user = new User(mEditText.getText().toString());
        preferences.edit().putString(Firstname, user.getFirstName()).apply();
        Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
        startActivityForResult(gameActivity, ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CODE && resultCode == RESULT_OK)    {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            user.setScore(score);
            TextView text =  findViewById(R.id.txt_welcome);
            text.setText(getText(R.string.txt_score) + " " +  score);
            preferences.edit().putInt(SCORE, user.getScore()).apply();
        }
    }
}