package com.example.alan_.agendatec.Login;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alan_.agendatec.R;

public class LoginActivity extends AppCompatActivity {
    //Declaracion de variables
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
        //Agrega al transaction el fragmento que tendra por default
        transaction.replace(R.id.framelogin, new FragmentLogin()).commit();
        //Asigna el titulo a la aplicación
        setTitle("Iniciar Sesión");
        //Escuchador del tablayout (Las pestañas de arriba)
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            //Metodo que se ejecuta cada que seleccionamos una pestaña del tablayout
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                currentPosition = tab.getPosition();
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                //CurrentPosition obtiene la posicion que se ha seleccionado de la pestaña y muestra su respectivo fragmento
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
