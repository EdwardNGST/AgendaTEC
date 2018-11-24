package com.example.alan_.agendatec.MainPanel;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alan_.agendatec.Model.Adapters.ResumeAdapter;
import com.example.alan_.agendatec.Model.Adapters.TasksAdapter;
import com.example.alan_.agendatec.Model.Class.Tasks;
import com.example.alan_.agendatec.Model.LocalDB.DatabaseHelper;
import com.example.alan_.agendatec.Model.LocalDB.TableTasks;
import com.example.alan_.agendatec.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //Declaracion de variables
    //El DatabaseHelper es la base de nuestra base de datos local
    private DatabaseHelper localDB;
    //TableTasks tiene toda la estructura de nuestra tabla de tareas
    private TableTasks tasks;
    //Arreglo de la clase tareas
    private static ArrayList<Tasks> tasksList;
    //Instancia del Adaptador Resumen
    private static ResumeAdapter tasksAdapter;
    private RecyclerView rvListTasks;
    private Context context;
    private TextView lblHour;
    private TextView lblHourT;
    private Calendar rightNow;

    private int hora=0, minuto=0, segundo=0;
    private Thread iniReloj=null;
    private Runnable r;
    private boolean isUpdate = false;
    private String sec, min, hor;
    private String curTime;
    private Calendar c;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    //ESTE METODO NO LO UTILIZO
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
    //METODO onCreate
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        context=this.getContext();
        //LocalDB se utilizara para obtener los datos de la base de datos local
        localDB = new DatabaseHelper(context);
        //El siguiente cursor traera los datos de la tabla tareas
        Cursor res=localDB.getTasks();

        rvListTasks=view.findViewById(R.id.rvListTasks);
        tasksList=new ArrayList<>();
        //Se agrega la lista de tareas al adaptador
        tasksAdapter=new ResumeAdapter(tasksList);

        lblHour=view.findViewById(R.id.lblHour);
        lblHourT=view.findViewById(R.id.lblHourT);

        rvListTasks.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());

        rvListTasks.setLayoutManager(manager);
        //Se agrega al recyclerview el adaptador
        rvListTasks.setAdapter(tasksAdapter);
        rvListTasks.setHasFixedSize(true);

        r=new RefreshClock();
        iniReloj=new Thread(r);
        iniReloj.start();

        //Recorre el cursor
        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            //Cada una de las posiciones del registro obtenido por el cursor se asignan a una variable
            int id = res.getInt(0);
            String title = res.getString(1);
            String text = res.getString(2);
            int priority = res.getInt(3);
            String date = res.getString(4);

            //Con esto verificaremos si la prioridad de la tarea es "Urgente" ya que queremos mostrar solo las tareas Urgentes
            if(priority==1) {
                //Se agrega la tarea a la lista que esta enlazada con el recyclerview, por lo tanto se actualiza el recyclerview
                tasksList.add(new Tasks(id, title, text));
            }
        }

        //Se retorna la vista
        return view;
    }

    private void updateTime(){
        c= Calendar.getInstance();
        hora=c.get(Calendar.HOUR);
        minuto=c.get(Calendar.MINUTE);

        setZeroClock();
    }

    private void settingNewClock(){
        setZeroClock();

        if (minuto>=0&minuto<=59){

        }else{
            minuto=0;
            hora+=1;
        }

        if (hora>=0&hora<=24){

        }else{
            hora=0;
        }
    }

    private void setZeroClock(){
        if (hora>=0&hora<=9){
            hor="0";
        }else{
            hor="";
        }

        if (minuto>=0&minuto<=9){
            min=":0";
        }else {
            min=":";
        }
    }

    private void initClock(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (isUpdate){
                        settingNewClock();
                    }else{
                        updateTime();
                    }
                    curTime=hor+hora+min+minuto;
                    lblHour.setText(curTime);
                    byte h = (byte) c.get(Calendar.HOUR_OF_DAY);
                    if (h<12){
                        lblHourT.setText("AM");
                    }else{
                        lblHourT.setText("PM");
                    }
                }catch (Exception e){}
            }
        });
    }

    class RefreshClock implements Runnable{
        @SuppressWarnings("unused")
        public void run(){
            while (!Thread.currentThread().isInterrupted()){
                try {
                    initClock();
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().isInterrupted();
                }catch (Exception ignored){}
            }
        }
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
