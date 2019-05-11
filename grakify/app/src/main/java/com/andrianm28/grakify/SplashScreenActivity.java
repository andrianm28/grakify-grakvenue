package com.andrianm28.grakify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
        finish();
    }
}