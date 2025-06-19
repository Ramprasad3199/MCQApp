package com.android.mcqapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText emailInput, passwordInput;
    Button loginBtn;
    TextView signupRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signupRedirect = findViewById(R.id.signupRedirect);

        loginBtn.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            int status = UserStorage.checkUserStatus(this, email, password);
            if (status == 1) {
                getSharedPreferences("session", MODE_PRIVATE)
                        .edit()
                        .putString("loggedInUser", email)
                        .putBoolean("isLoggedIn", true)
                        .apply();

                Intent i = new Intent(this, DashboardActivity.class);
                i.putExtra("user", email);
                startActivity(i);
            } else if (status == 2) {
                Toast.makeText(this, "You have already submitted the quiz. Login denied.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "User not found. Redirecting to signup.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SignupActivity.class));
            }
        });


        signupRedirect.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}
