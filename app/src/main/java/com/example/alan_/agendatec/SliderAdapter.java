package com.example.alan_.agendatec;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class SliderAdapter extends PagerAdapter{
    private Context context;

    SliderAdapter(Context context){
        this.context=context;
    }

    //Arrays
    private int[] slide_images={
            R.drawable.slider_screen1,
            R.drawable.slider_screen2,
            R.drawable.slider_screen3
    };

    private String[] slide_headings={
            "Bienvenido a AgendaTEC",
            "Tareas",
            "Contactos"
    };

    private String[] slide_descs={
            "Esta aplicación le permitira tener una mejor organización de sus tareas",
            "Agregue sus tareas asignando su prioridad para tener un mayor control de lo que tiene que hacer en el dia",
            "Su agenda le permite agregar contactos para poder interactuar con ellos dentro de la aplicación o mandarles correos electrónicos"
    };

    @Override
    public int getCount(){
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = Objects.requireNonNull(layoutInflater).inflate(R.layout.slider_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.imgBackgroundSlider);
        TextView slideHeading = view.findViewById(R.id.lblSliderTitle);
        TextView slideDescription = view.findViewById(R.id.lblSliderDesc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((RelativeLayout)object);
    }
}
