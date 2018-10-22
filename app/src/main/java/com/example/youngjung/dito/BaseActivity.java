package com.example.youngjung.dito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.youngjung.dito.Login.KaKaoSignupActivity;
import com.example.youngjung.dito.Login.LoginActivity;
import com.example.youngjung.dito.View.MainActivity;

public class BaseActivity extends AppCompatActivity {
    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    protected void redirectMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, KaKaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
