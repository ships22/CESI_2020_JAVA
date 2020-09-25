package com.cesi.model;

import java.util.ArrayList;
import java.util.List;


public class Question {

    private String question;
    private List<String> choice = new ArrayList<String>();
    private int ans;
    private String description;

    public Question(String question, List<String> choice, int ans, String description) {
        this.question = question;
        this.choice = choice;
        this.ans = ans;
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoice() {
        return choice;
    }

    public void setChoice(List<String> choice) {
        this.choice = choice;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
