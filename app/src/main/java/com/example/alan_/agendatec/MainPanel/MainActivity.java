package com.example.alan_.agendatec.MainPanel;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.alan_.agendatec.Login.FragmentLogin;
import com.example.alan_.agendatec.R;
/***********************************************************************
*ESTE ES EL ACTIVITY QUE CONTENDRA EL BottomNavigationView QUE PERMITIRA
 * MOVERNOS ENTRE LAS INTERFACES PRINCIPALES DE NUESTRA APLICACIÓN
 **********************************************************************/
public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //Asigna el fragmento que utilizaremos por default
        transaction.replace(R.id.frame, new MainFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Marca que utilizaremos la primera opcion del menu
        navigation.getMenu().getItem(0).setChecked(true);


    }
    //Listener del BottomNavigationView
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //Cambia el fragment dependiendo de la opcion que seleccionemos en el navigation
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.frame, new MainFragment()).commit();
                    return true;
                case R.id.navigation_tasks:
                    transaction.replace(R.id.frame, new TasksFragment()).commit();
                    return true;
                case R.id.navigation_config:
                    transaction.replace(R.id.frame, new OptionsFragment()).commit();
            }
            return false;
        }
    };
}
