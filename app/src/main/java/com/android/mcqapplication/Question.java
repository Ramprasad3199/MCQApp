package com.android.mcqapplication;

public class Question {
    public String question;
    public String[] options;
    public String correct;

    public Question(String question, String[] options, String correct) {
        this.question = question;
        this.options = options;
        this.correct = correct;
    }
}
