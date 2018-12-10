    package com.example.alan_.agendatec.MainPanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alan_.agendatec.Model.Adapters.TasksAdapter;
import com.example.alan_.agendatec.Model.Class.Tasks;
import com.example.alan_.agendatec.Model.LocalDB.DatabaseHelper;
import com.example.alan_.agendatec.Model.LocalDB.TableTasks;
import com.example.alan_.agendatec.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

/**
 ESTA CLASE CONTIENE LOS METODOS UTILIZADOS POR EL FRAGMENTO DE TAREAS
 */
public class TasksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    //Declaracion de variables
    //El DatabaseHelper es la base de nuestra base de datos local
    private DatabaseHelper localDB;
    private Context context;
    //El alertdialog funciona como un modal
    private AlertDialog dialog;
    //Variable auxiliar que probablemente no se utilice
    private boolean aux=false;
    private byte priority=0;

    private EditText txtTitleNewTask;
    private EditText txtDescNewTask;
    private TextView lblDateNewTask, lblDay, lblDate;
    private FloatingActionButton fab;
    private ImageView btnHelp;

    private static int idMax = 0;
    private static final String TAG = "TaskFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //Arreglo de la clase tareas
    private static ArrayList<Tasks> tasksList;
    //Instancia del Adaptador de Tareas
    private static TasksAdapter tasksAdapter;
    private RecyclerView rvListTasks;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //ESTE METODO NO LO UTILIZO
    public TasksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TasksFragment.
     */
    //ESTE METODO NO LO UTILIZO
    // TODO: Rename and change types and number of parameters
    public static TasksFragment newInstance(String param1, String param2) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //ESTE METODO NO LO UTILIZO
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        context=this.getContext();
        //LocalDB se utilizara para obtener los datos de la base de datos local
        localDB = new DatabaseHelper(context);
        //El siguiente cursor traera los datos de la tabla tareas
        Cursor res=localDB.getTasks();

        CalendarView mCalendarView = view.findViewById(R.id.calendar_view);
        //Obtiene la fecha actual
        long a = mCalendarView.getDate();
        //Inhabilita las fechas anteriores a la actual, ya que no podemos agregar una tarea de una fecha que ya paso
        mCalendarView.setMinDate(a);

        rvListTasks=view.findViewById(R.id.rvListTasks);
        tasksList=new ArrayList<>();
        //Se agrega la lista de tareas al adaptador
        tasksAdapter=new TasksAdapter(tasksList);

        rvListTasks.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvListTasks.setLayoutManager(manager);


        /*TasksAdapter.RecyclerViewClickListener listener = (v, position) -> {
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
        };*/



        //Se agrega al recyclerview el adaptador
        rvListTasks.setAdapter(tasksAdapter);
        rvListTasks.setHasFixedSize(true);
        //El siguiente metodo muestra la lista
        showList(res);

        fab=view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("");
            }
        });

        lblDay = view.findViewById(R.id.lblDay);
        lblDate = view.findViewById(R.id.lblDate);
        btnHelp = view.findViewById(R.id.btnHelp);
        Calendar c = Calendar.getInstance();
        int dayOfWeek=c.get(Calendar.DAY_OF_WEEK);
        int numberDayOfMonth=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        switch (month){
            case 1:
                lblDate.setText(numberDayOfMonth+" de Febrero");
                break;
            case 2:
                lblDate.setText(numberDayOfMonth+" de Marzo");
                break;
            case 3:
                lblDate.setText(numberDayOfMonth+" de Abril");
                break;
            case 4:
                lblDate.setText(numberDayOfMonth+" de Mayo");
                break;
            case 5:
                lblDate.setText(numberDayOfMonth+" de Junio");
                break;
            case 6:
                lblDate.setText(numberDayOfMonth+" de Julio");
                break;
            case 7:
                lblDate.setText(numberDayOfMonth+" de Agosto");
                break;
            case 8:
                lblDate.setText(numberDayOfMonth+" de Septiembre");
                break;
            case 9:
                lblDate.setText(numberDayOfMonth+" de Octubre");
                break;
            case 10:
                lblDate.setText(numberDayOfMonth+" de Noviembre");
                break;
            case 11:
                lblDate.setText(numberDayOfMonth+" de Diciembre");
                break;
            case 12:
                lblDate.setText(numberDayOfMonth+" de Enero");
                break;
            default:
                lblDate.setText("");
        }
        switch (dayOfWeek){
            case 1:
                lblDay.setText("Domingo");
                break;
            case 2:
                lblDay.setText("Lunes");
                break;
            case 3:
                lblDay.setText("Martes");
                break;
            case 4:
                lblDay.setText("Miercoles");
                break;
            case 5:
                lblDay.setText("Jueves");
                break;
            case 6:
                lblDay.setText("Viernes");
                break;
            case 7:
                lblDay.setText("Sabado");
                break;
            default:
                lblDay.setText("");
        }

        //Escuchador del calendario, obtiene el dia mes y año y lo envia al dialogo de inserccion de tareas para despues mostrar este
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                    String date = i2 + "/" + (i1 + 1) + "/" + i;
                    showDialog(date);
                }
            }
        );

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Alert message to be shown");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continuar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        return view;
    }

    private void showList(Cursor res) {
        //Recorre el cursor
        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            //Cada una de las posiciones del registro obtenido por el cursor se asignan a una variable
            int id = res.getInt(0);
            String title = res.getString(1);
            String text = res.getString(2);
            int priority = res.getInt(3);
            String date = res.getString(4);
            //Con lo siguiente verificaremos que prioridad tiene el elemento del cursor y dependiendo de esto agregaremos la tarea a la lista con el mensaje (Urgente/Importante/Normal) dependiendo de la prioridad
            if(priority==1) {
                tasksList.add(new Tasks(id, title, text, (byte) priority, date + "\n Urgente"));
            }else if(priority==2){
                tasksList.add(new Tasks(id, title, text, (byte) priority, date + "\n Importante"));
            }else if(priority==3){
                tasksList.add(new Tasks(id, title, text, (byte) priority, date + "\n Normal"));
            }else{
                tasksList.add(new Tasks(id, title, text, (byte) priority, date));
            }
        }
    }

    private void showDialog(final String date){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        @SuppressLint("InflateParams") View mView = getLayoutInflater().inflate(R.layout.dialog_new_task, null);

        TextView lblNewTask =  mView.findViewById(R.id.lblNewTask);
        txtTitleNewTask = mView.findViewById(R.id.txtTitleNewTask);
        txtDescNewTask = mView.findViewById(R.id.txtDescNewTask);
        lblDateNewTask = mView.findViewById(R.id.lblDateNewTask);
        Button btnCleanDate = mView.findViewById(R.id.btnCleanDate);
        RadioGroup rgPriorityNewTask = mView.findViewById(R.id.rgPriorityNewTask);
        RadioButton rbPriorityUrgent = mView.findViewById(R.id.rbPriorityUrgent);
        RadioButton rbPriorityImportant = mView.findViewById(R.id.rbPriorityImportant);
        RadioButton rbPriorityNormal = mView.findViewById(R.id.rbPriorityNormal);
        Button btnRegister = mView.findViewById(R.id.btnRegister);
        //Listener del radiogroup que nos permite al dar en registrar saber que radiobutton esta seleccionado
        rgPriorityNewTask.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbPriorityUrgent:
                        priority=1;
                        break;
                    case R.id.rbPriorityImportant:
                        priority=2;
                        break;
                    case R.id.rbPriorityNormal:
                        priority=3;
                        break;
                }
            }
        });

        //calendar_view.setVisibility(View.INVISIBLE);

        if (date != "") {
            lblDateNewTask.setText("Fecha: " + date);
        }else{
            lblDateNewTask.setText("Fecha: Sin Asignar");
        }

        //Escuchador del boton que permite agregar una tarea sin fecha especificada
        btnCleanDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblDateNewTask.setText("Fecha: Sin Asignar");
            }
        });
        //Escuchador del boton que nos permite crear la tarea nueva
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=localDB.getTasks();
                //Recorre el cursor (tabla de tareas) que obtendra el id maximo del cursor
                for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                    idMax= res.getInt(0);
                }
                //idMax guardara el id que tendra la siguiente tarea insertada
                idMax=idMax+1;
                //Inserta la nueva tarea
                if(priority!=0) {
                    localDB.insertNewTask(txtTitleNewTask.getText().toString(), txtDescNewTask.getText().toString(), priority, date);
                    Toast.makeText(context, "Elemento insertado", Toast.LENGTH_SHORT).show();
                }
                //Oculta el dialogo
                dialog.hide();
            }
        });
        //Se enlaza la vista con el Builder
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        //Muestra el dialogo
        dialog.show();
    }

    //ESTE METODO NO LO UTILIZO
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //ESTE METODO NO LO UTILIZO
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    //ESTE METODO NO LO UTILIZO
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    //ESTE METODO NO LO UTILIZO
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
