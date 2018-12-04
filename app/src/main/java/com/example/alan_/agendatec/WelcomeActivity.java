package com.example.alan_.agendatec;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    //Declaracion de variables del slider
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    //Variable que definira la posicion actual del slider
    private int mCurrentPage=0;
    //Declaracion de botones y textview
    private Button btnContinue, btnImport;
    private TextView btnPrivacyPolicies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);

        btnContinue = findViewById(R.id.btnContinue);
        btnImport = findViewById(R.id.btnImportData);
        btnPrivacyPolicies = findViewById(R.id.btnPrivacyPolicies);

        btnImport.setVisibility(View.INVISIBLE);
        btnContinue.setVisibility(View.INVISIBLE);
        btnPrivacyPolicies.setVisibility(View.INVISIBLE);
        //Declaracion del adaptador basado en la clase SliderAdapter
        SliderAdapter sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        //Ejecuta el metodo que definira el color del circulo dependiendo de en que posicion del slider nos encontremos
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        timer();
        //start();
    }

    /*public void start(){
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), com.example.alan_.agendatec.Login.LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }*/
    //Metodo que definira el color del circulo dependiendo de en que posicion del slider nos encontremos
    public void addDotsIndicator(int position){
        //Define la cantidad de circulos
        TextView[] mDots = new TextView[3];
        mDotLayout.removeAllViews();
        //Se van creando los circulos y rellenando dependiendo del orden del slider
        for (int i = 0; i< mDots.length; i++){
            mDots[i]=new TextView(this);
            //El siguiente codigo (&#8226) es un circulo en el codigo ASCI
            mDots[i].setText(Html.fromHtml("&#8226"));
            //Define el tamaño del circulo
            mDots[i].setTextSize(35);
            //Color del circulo cuando esta inactivo
            mDots[i].setTextColor(getResources().getColor(R.color.white));
            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length>0){
            //Color del circulo cuando esta activo
            mDots[position].setTextColor(getResources().getColor(R.color.primaryBlueWhite));
        }
    }

    //Escuchador de eventos del slider
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        //Esto se ejecuta cada que cambia la posicion de la pagina del slider
        @Override
        public void onPageSelected(int position) {
            //Se vuelve a desmarcar un circulo para marcarse el siguiente
            addDotsIndicator(position);
            mCurrentPage=position;
            //Esto nos ayudara a especificar que cuando estemos en la tercera pagina se mostraran los botones de inicio
            if (mCurrentPage==2){
                btnImport.setVisibility(View.VISIBLE);
                btnContinue.setVisibility(View.VISIBLE);
                btnPrivacyPolicies.setVisibility(View.VISIBLE);
            }else{
                btnImport.setVisibility(View.INVISIBLE);
                btnContinue.setVisibility(View.INVISIBLE);
                btnPrivacyPolicies.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };

    //Metodo que nos ayuda a cambiar la posicion del slider automaticamente
    private void timer(){
        Thread t=new Thread(){
            int count=1;
            @Override
            public void run(){
                //Este while permitira crear un bucle infinito, lo que significa que siempre se estara cambiando de posicion
                while(count==1){
                    try {
                        //El tiempo que tardara en cambiar el slider
                        Thread.sleep(10000);  //1000ms = 1 sec
                        //Esto se ejecutara despues de los 10 segundos
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Este contador nos permitira definir que cuando la posicion del slider sea 3 se regrese a 0 para volver a mostrar las primeras pantallas hasta llegar denuevo a 3
                                if (mCurrentPage>=3){
                                    //Se reinicia el contador
                                    mCurrentPage=0;
                                    //Asigna al slider la pantalla dependiendo de la posicion del contador
                                    mSlideViewPager.setCurrentItem(mCurrentPage);
                                }else{
                                    //Aumenta el contador y asigna la posicion nueva
                                    mCurrentPage=mCurrentPage+1;
                                    mSlideViewPager.setCurrentItem(mCurrentPage);
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    //Este metodo nos mandara a la pantalla de login para importar datos desde la nube
    public void importData(View view) {
        Intent intent = new Intent (getApplicationContext(), com.example.alan_.agendatec.Login.LoginActivity.class);
        startActivityForResult(intent, 0);
    }

    //Este metodo nos mandara a la pantalla para manejar nuestras tareas
    public void btnContinue(View view) {
        Intent intent = new Intent (getApplicationContext(), com.example.alan_.agendatec.MainPanel.MainActivity.class);
        startActivityForResult(intent, 0);
    }
}