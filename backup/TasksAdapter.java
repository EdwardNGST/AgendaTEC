package com.example.alan_.agendatec.Model.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alan_.agendatec.MainActivity;
import com.example.alan_.agendatec.MainPanel.TasksFragment;
import com.example.alan_.agendatec.Model.Class.Tasks;
import com.example.alan_.agendatec.R;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ContainerView>{
    private ArrayList<Tasks> tasksList;
    private RecyclerViewClickListener mListener;

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;

        RowViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    //ESTE METODO POR AHORA NO HACE MUCHO
    @NonNull
    @Override
    public TasksAdapter.ContainerView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tasks,
                viewGroup, false);
        //view.setOnClickListener(TasksFragment.eventOnClickListener);

        return new ContainerView(view, mListener);
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
    public class ContainerView extends RecyclerView.ViewHolder  implements View.OnClickListener{
        ImageView priorityColor;
        TextView lblTitleTask;
        TextView lblTextTask;
        TextView lblDateTask;

        private RecyclerViewClickListener mListener;
        public ContainerView(@NonNull View view, RecyclerViewClickListener listener) {
            super(view);

            this.priorityColor = view.findViewById(R.id.priorityColor);
            this.lblTitleTask = view.findViewById(R.id.lblTitleTask);
            this.lblTextTask = view.findViewById(R.id.lblTextTask);
            this.lblDateTask = view.findViewById(R.id.lblDateTask);

            mListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //mListener.onClick(view, getAdapterPosition());
            final Context context;
            context=view.getContext();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
