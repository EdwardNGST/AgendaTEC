package com.example.alan_.agendatec.Login;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alan_.agendatec.R;

public class LoginActivity extends AppCompatActivity {
    private int currentPosition;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TabLayout tabLayout = findViewById(R.id.tabFilter);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.framelogin, new FragmentLogin()).commit();
        setTitle("Iniciar Sesión");

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                currentPosition = tab.getPosition();
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                if(currentPosition==0){
                    transaction.replace(R.id.framelogin, new FragmentLogin()).commit();
                }else if(currentPosition==1){
                    transaction.replace(R.id.framelogin, new FragmentLogon()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
