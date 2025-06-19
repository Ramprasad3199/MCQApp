package com.android.mcqapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {
    EditText emailInput, passwordInput;
    Button signupBtn;

    TextView loginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signupBtn);
        loginRedirect = findViewById(R.id.loginRedirect);


        signupBtn.setOnClickListener(v -> {
            UserStorage.saveUser(this, emailInput.getText().toString(), passwordInput.getText().toString());
            Toast.makeText(this, "User registered!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        });

        loginRedirect.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

    }
}
