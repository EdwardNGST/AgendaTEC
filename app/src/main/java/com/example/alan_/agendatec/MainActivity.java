package com.example.alan_.agendatec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se crea el intent guardando la posicion de la actividad principal
        Intent intent = new Intent (getApplicationContext(), com.example.alan_.agendatec.WelcomeActivity.class);
        //Este metodo nos dirige hacia el elemento guardado en el intent (WelcomeActivity)
        startActivityForResult(intent, 0);
        //Vacia la pila de posiciones que se ha ido generando, esto evitara que puedamos regresar a esta clase.
        finish();
    }
}