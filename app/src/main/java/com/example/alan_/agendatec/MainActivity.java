package com.example.alan_.agendatec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent (getApplicationContext(), com.example.alan_.agendatec.WelcomeActivity.class);
        startActivityForResult(intent, 0);
    }
}