package com.example.alan_.agendatec.Model.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alan_.agendatec.Model.Class.Tasks;
import com.example.alan_.agendatec.R;

import java.util.ArrayList;

public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ContainerView> {
    private ArrayList<Tasks> tasksList;

    public ResumeAdapter(ArrayList<Tasks> tasks){ this.tasksList=tasks; }
    //ESTE METODO POR AHORA NO HACE MUCHO
    @NonNull
    @Override
    public ResumeAdapter.ContainerView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_resume,
                viewGroup, false);
        return new ContainerView(view);
    }

    //Este metodo asigna los textos a los elementos
    @Override
    public void onBindViewHolder(@NonNull ResumeAdapter.ContainerView containerView, int i) {
        final Tasks tasks=tasksList.get(i);
        containerView.lblTitleTask.setText(tasks.getTitle());
        containerView.lblTextTask.setText(tasks.getText());
    }

    //Obtiene un valor de la cantidad de elementos en la lista
    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    //Declaracion de variables
    public class ContainerView extends RecyclerView.ViewHolder {
        TextView lblTitleTask;
        TextView lblTextTask;
        public ContainerView(@NonNull View itemView) {
            super(itemView);

            this.lblTitleTask=itemView.findViewById(R.id.lblTitleTask);
            this.lblTextTask=itemView.findViewById(R.id.lblTextTask);
        }
    }
}
