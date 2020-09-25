package com.cesi.topquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cesi.model.Question;
import com.cesi.model.QuestionBank;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {
    private QuestionBank questionBank;
    private TextView textView;
    private Button[] buttons = new Button[4];
    private Question currentQuestion;
    private int score = 0;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private final GameActivity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        questionBank = generateQuestions();
        textView = findViewById(R.id.textView);
        buttons[0] = findViewById(R.id.ans_btn_1);
        buttons[1] = findViewById(R.id.ans_btn_2);
        buttons[2] = findViewById(R.id.ans_btn_3);
        buttons[3] = findViewById(R.id.ans_btn_4);
        displayQuestion();
    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"),
                0,
                "On 23 septembre 2008, Andy Rubin launched Android.");
        Question question2 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958",
                        "1962",
                        "1967",
                        "1969"),
                3,
                "On July 20, 1969, Neil Armstrong landed on the moon for  the  first time.");
        Question question3 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42",
                        "101",
                        "666",
                        "742"),
                3,
                "Simpsons used to live at 742 Evergreen Terrace in Springfield.");
        Question question4 = new Question("Who is the first president of France",
                Arrays.asList("Napoléon Bonaparte",
                        "Adolphe Thiers",
                        "Jules Grévy",
                        "Jean Casimir-Perier"),
                0,
                "Who was elected in the 1848 election.");
        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4));
    }

    private void displayQuestion() {

        currentQuestion = questionBank.getQuestion();
        this.textView.setText(currentQuestion.getQuestion());
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(currentQuestion.getChoice().get(i));
        }
    }

    public void onAnswer(View view) {
        int resp = Integer.parseInt((String) view.getTag());
        if (resp == currentQuestion.getAns()) {
            //réponse correct -
            Toast.makeText(this, "Correct !", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            //mauvaise réponse -
            String correctAns = currentQuestion.getChoice().get(currentQuestion.getAns());
            Toast.makeText(this, "Incorrect ! La bonne réponse est : " + correctAns +"." + currentQuestion.getDescription(), Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override    public void run()
            {
                if (questionBank.remainingQuestion() > 0) {
                    displayQuestion();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Parfait !")
                            .setMessage("Votre score est " + score)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent();
                                    intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            }).create().show();
                }
            }
            }, 3000); // LENGTH_SHORT is usually 2 second long
    }
}