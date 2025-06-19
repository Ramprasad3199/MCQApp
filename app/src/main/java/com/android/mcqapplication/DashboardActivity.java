package com.android.mcqapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView welcomeMsg = findViewById(R.id.welcome);
        Button startQuizBtn = findViewById(R.id.startQuizBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);

        String user = getIntent().getStringExtra("user");
        welcomeMsg.setText("Welcome," + user+"\n\nPress \"Start Quiz\" to begin.\nYou will be presented with 10 multiple-choice questions");


        // Save user in session SharedPreferences
        getSharedPreferences("session", MODE_PRIVATE)
                .edit().putString("loggedInUser", user).apply();

        startQuizBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("questionIndex", 0);
            intent.putExtra("answers", new String[10]);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });


        logoutBtn.setOnClickListener(v -> {
            getSharedPreferences("session", MODE_PRIVATE)
                    .edit()
                    .remove("loggedInUser")
                    .putBoolean("isLoggedIn", false)
                    .apply();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

    }
}
