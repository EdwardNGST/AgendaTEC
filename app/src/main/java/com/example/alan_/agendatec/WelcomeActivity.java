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
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;
    private int mCurrentPage=0;

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

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        //start=(Button)findViewById(R.id.btnStart);

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

    public void addDotsIndicator(int position){
        mDots=new TextView[3];
        mDotLayout.removeAllViews();
        for (int i=0; i<mDots.length; i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.white));
            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length>0){

            mDots[position].setTextColor(getResources().getColor(R.color.primaryBlueWhite));
        }

    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage=position;
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

    private void timer(){
        Thread t=new Thread(){
            int count=1;
            @Override
            public void run(){
                while(count==1){
                    try {
                        Thread.sleep(10000);  //1000ms = 1 sec
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mCurrentPage>=3){
                                    mCurrentPage=0;
                                    mSlideViewPager.setCurrentItem(mCurrentPage);
                                }else{
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

    public void importData(View view) {
        Intent intent = new Intent (getApplicationContext(), com.example.alan_.agendatec.Login.LoginActivity.class);
        startActivityForResult(intent, 0);
    }

    public void btnContinue(View view) {
        Intent intent = new Intent (getApplicationContext(), com.example.alan_.agendatec.MainPanel.MainActivity.class);
        startActivityForResult(intent, 0);
    }
}