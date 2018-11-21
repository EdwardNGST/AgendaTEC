package com.example.alan_.agendatec.Model.Adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan_.agendatec.MainActivity;
import com.example.alan_.agendatec.MainPanel.TasksFragment;
import com.example.alan_.agendatec.Model.Class.Tasks;
import com.example.alan_.agendatec.R;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ContainerView>{
    private ArrayList<Tasks> tasksList;
    //ESTE METODO POR AHORA NO HACE MUCHO
    @NonNull
    @Override
    public TasksAdapter.ContainerView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tasks,
                viewGroup, false);
        //view.setOnClickListener(TasksFragment.eventOnClickListener);

        return new ContainerView(view);
    }

    public TasksAdapter(ArrayList<Tasks> tasks){
        this.tasksList=tasks;
    }

    //Este metodo asigna los textos a los elementos
    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.ContainerView containerView, int position) {
        final Tasks tasks = tasksList.get(position);

        containerView.lblTitleTask.setText(tasks.getTitle());
        containerView.lblTextTask.setText(tasks.getText());
        containerView.lblDateTask.setText(tasks.getDate());

        switch (tasks.getPriority()){
            case 1:
                containerView.priorityColor.setBackgroundColor(Color.parseColor("#FF0000"));
                containerView.lblDateTask.setTextColor(Color.parseColor("#FF0000"));
                break;
            case 2:
                containerView.priorityColor.setBackgroundColor(Color.parseColor("#FFFFEA00"));
                containerView.lblDateTask.setTextColor(Color.parseColor("#FFFFEA00"));
                break;
            case 3:
                containerView.priorityColor.setBackgroundColor(Color.parseColor("#FF64DD17"));
                containerView.lblDateTask.setTextColor(Color.parseColor("#FF64DD17"));
                break;
            default:
                containerView.priorityColor.setBackgroundColor(Color.parseColor("#FFF"));
                containerView.lblDateTask.setTextColor(Color.parseColor("#2D2D2D"));
        }
    }

    //Obtiene un valor de la cantidad de elementos en la lista
    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    //Declaracion de variables
    public class ContainerView extends RecyclerView.ViewHolder {
        ImageView priorityColor;
        TextView lblTitleTask;
        TextView lblTextTask;
        TextView lblDateTask;
        public ContainerView(@NonNull View view) {
            super(view);

            this.priorityColor = view.findViewById(R.id.priorityColor);
            this.lblTitleTask = view.findViewById(R.id.lblTitleTask);
            this.lblTextTask = view.findViewById(R.id.lblTextTask);
            this.lblDateTask = view.findViewById(R.id.lblDateTask);
        }
    }
}
