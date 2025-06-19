package com.android.mcqapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Your splash screen layout

        new Handler().postDelayed(() -> {
            SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
            boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

            Intent intent;
            if (isLoggedIn) {
                // Auto-login: Go to Dashboard
                intent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
            } else {
                // Not logged in: Go to Login
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }, 2500); // 2.5-second delay
    }
}
