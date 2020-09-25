package com.cesi.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuestionBank {
    private int lastIndex = 0;
    private List<Question> questions = new ArrayList<Question> ();

    public QuestionBank(List<Question> questions) {
        Collections.shuffle(questions);
        this.questions = questions;
    }

    public Question getQuestion() {
        Question q = questions.get(lastIndex);
        lastIndex++;
//        if (lastIndex >= questions.size()) {
//            lastIndex = 0;
//        }
        return q;
    }
    public int getNBQuestion() {
        return 0;
    }

    public int remainingQuestion() {
        return questions.size() - lastIndex;
    }
    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }
}
