package com.android.mcqapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity extends AppCompatActivity {
    TextView questionText;
    RadioGroup optionsGroup;
    ProgressBar progressBar;
    Button nextBtn, backBtn;
    int index;
    String[] userAnswers;

    public static Question[] questions = {
            new Question("Which company developed Android?",
                    new String[]{"Apple", "Google", "Microsoft", "Samsung"},
                    "Google"),

            new Question("Which component is NOT part of Android architecture?",
                    new String[]{"Linux Kernel", "Middleware", "Web Server", "Application Framework"},
                    "Web Server"),

            new Question("What does APK stand for?",
                    new String[]{"Android Phone Kit", "Android Package", "Application Package Kit", "Application Program Kernel"},
                    "Application Package Kit"),

            new Question("Which file contains the permissions required by an app?",
                    new String[]{"MainActivity.java", "AndroidManifest.xml", "Build.gradle", "Settings.gradle"},
                    "AndroidManifest.xml"),

            new Question("Which layout arranges elements linearly?",
                    new String[]{"RelativeLayout", "LinearLayout", "FrameLayout", "ConstraintLayout"},
                    "LinearLayout"),

            new Question("Which method is called when an activity is first created?",
                    new String[]{"onStart()", "onResume()", "onCreate()", "onInit()"},
                    "onCreate()"),

            new Question("Which data type is used to store true/false values?",
                    new String[]{"int", "boolean", "char", "float"},
                    "boolean"),

            new Question("Which component is used to display a scrollable list?",
                    new String[]{"RecyclerView", "TextView", "ImageView", "Button"},
                    "RecyclerView"),

            new Question("Which keyword is used to inherit a class in Java?",
                    new String[]{"this", "super", "extends", "implements"},
                    "extends"),

            new Question("Which method is used to start another activity?",
                    new String[]{"startActivity()", "run()", "startService()", "initActivity()"},
                    "startActivity()")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        progressBar = findViewById(R.id.progressBar);
        nextBtn = findViewById(R.id.nextBtn);
        backBtn = findViewById(R.id.backBtn);

        index = getIntent().getIntExtra("questionIndex", 0);
        userAnswers = (String[]) getIntent().getSerializableExtra("answers");

        loadQuestion();

        nextBtn.setOnClickListener(v -> {
            int selectedId = optionsGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selected = findViewById(selectedId);
            userAnswers[index] = selected.getText().toString();

            if (index < 9) {
                Intent i = new Intent(this, QuestionActivity.class);
                i.putExtra("questionIndex", index + 1);
                i.putExtra("answers", userAnswers);
                navigateWithFade(i);  // << use animation
            } else {
                Intent i = new Intent(this, ReviewActivity.class);
                i.putExtra("answers", userAnswers);
                navigateWithFade(i);  // << use animation
            }
        });


        backBtn.setOnClickListener(v -> {
            if (index > 0) {
                Intent i = new Intent(this, QuestionActivity.class);
                i.putExtra("questionIndex", index - 1);
                i.putExtra("answers", userAnswers);
                navigateWithFade(i);  // << use animation
            } else {
                Toast.makeText(this, "This is the first question", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadQuestion() {
        Question q = questions[index];
        questionText.setText("Q" + (index + 1) + ": " + q.question);
        optionsGroup.removeAllViews();

        for (String option : q.options) {
            RadioButton rb = new RadioButton(this);
            rb.setText(option);
            optionsGroup.addView(rb);
        }

        // Pre-select previously selected answer
        if (userAnswers[index] != null) {
            for (int i = 0; i < optionsGroup.getChildCount(); i++) {
                RadioButton rb = (RadioButton) optionsGroup.getChildAt(i);
                if (rb.getText().toString().equals(userAnswers[index])) {
                    rb.setChecked(true);
                    break;
                }
            }
        }

        // Progress Bar Update (10 questions → step = 10%)
        int progress = (int) (((index + 1) / 10.0) * 100);
        progressBar.setProgress(progress);
    }

    // Helper method
    private void navigateWithFade(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish(); // Optional: closes current activity to avoid back stack clutter
    }

}

