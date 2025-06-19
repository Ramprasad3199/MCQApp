package com.android.mcqapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AnswerReviewAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] userAnswers;

    public AnswerReviewAdapter(Context context, String[] userAnswers) {
        super(context, 0, userAnswers);
        this.context = context;
        this.userAnswers = userAnswers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_review_answer, parent, false);
        }

        TextView questionText = convertView.findViewById(R.id.questionText);
        TextView answerText = convertView.findViewById(R.id.answerText);

        // Get question from static list
        Question q = QuestionActivity.questions[position];

        questionText.setText("Q" + (position + 1) + ": " + q.question);
        answerText.setText("Your Answer: " + userAnswers[position]);

        return convertView;
    }
}
