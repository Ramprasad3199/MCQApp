package com.android.mcqapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReviewActivity extends AppCompatActivity {
    ListView reviewList;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ListView reviewList = findViewById(R.id.reviewList);
        submitBtn = findViewById(R.id.submitBtn);

        String[] answers = (String[]) getIntent().getSerializableExtra("answers");

        // 🔁 Use custom adapter
        AnswerReviewAdapter adapter = new AnswerReviewAdapter(this, answers);
        reviewList.setAdapter(adapter);

        submitBtn.setOnClickListener(v -> {
            String email = getSharedPreferences("session", MODE_PRIVATE).getString("loggedInUser", null);
            if (email != null) {
                UserStorage.markUserSubmitted(this, email);
            }

            Toast.makeText(this, "Answers submitted successfully!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

}

