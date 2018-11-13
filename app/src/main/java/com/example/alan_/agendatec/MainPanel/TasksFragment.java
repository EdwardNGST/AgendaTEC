    package com.example.alan_.agendatec.MainPanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alan_.agendatec.Model.LocalDB.DatabaseHelper;
import com.example.alan_.agendatec.Model.LocalDB.TableTasks;
import com.example.alan_.agendatec.R;

import org.w3c.dom.Text;

import java.util.Calendar;

    /**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TasksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private DatabaseHelper localDB;
    private Context context;
    private TableTasks tasks;
    private AlertDialog dialog;
    private boolean aux=false;
    private byte priority=0;

    private EditText txtTitleNewTask;
    private EditText txtDescNewTask;
    private TextView lblDateNewTask;

    private static int idMax = 0;
    private static final String TAG = "TaskFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
    // TODO: Rename and change types and number of parameters
    public static TasksFragment newInstance(String param1, String param2) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        localDB = new DatabaseHelper(context);

        tasks = new TableTasks();
        Cursor res=localDB.getTasks();

        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            int id = res.getInt(0);
            String title = res.getString(1);
            String text = res.getString(2);
            int priority = res.getInt(3);
            String date = res.getString(4);

            Log.i(TAG, "Registro numero: "+id+
                    ", titulo: "+title+
                    ", text: "+text+
                    ", priority: "+priority+
                    ", date: "+date);
        }

        CalendarView mCalendarView = view.findViewById(R.id.calendar_view);
        long a = mCalendarView.getDate();
        mCalendarView.setMinDate(a);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                    String date = i2 + "/" + (i1 + 1) + "/" + i;
                    showDialog(date);
                    //Log.i("TAG", "onSelectedTextChange: date: " + date);
                }
            }
        );

        return view;
    }

    private void showDialog(final String date){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        @SuppressLint("InflateParams") View mView = getLayoutInflater().inflate(R.layout.dialog_new_task, null);

        TextView lblNewTask =  mView.findViewById(R.id.lblNewTask);
        txtTitleNewTask = mView.findViewById(R.id.txtTitleNewTask);
        txtDescNewTask = mView.findViewById(R.id.txtDescNewTask);
        lblDateNewTask = mView.findViewById(R.id.lblDateNewTask);
        Button btnCleanDate = mView.findViewById(R.id.btnCleanDate);
        //final Button btnShowCalendar = mView.findViewById(R.id.btnShowCalendar);
        //final CalendarView calendar_view = mView.findViewById(R.id.calendar_view);
        RadioGroup rgPriorityNewTask = mView.findViewById(R.id.rgPriorityNewTask);
        RadioButton rbPriorityUrgent = mView.findViewById(R.id.rbPriorityUrgent);
        RadioButton rbPriorityImportant = mView.findViewById(R.id.rbPriorityImportant);
        RadioButton rbPriorityNormal = mView.findViewById(R.id.rbPriorityNormal);
        Button btnRegister = mView.findViewById(R.id.btnRegister);

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

        lblDateNewTask.setText("Fecha: " + date);

        /*btnShowCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux) {
                    btnShowCalendar.setText("Mostrar Calendario");
                    calendar_view.setVisibility(View.INVISIBLE);
                    aux=false;
                }else{
                    btnShowCalendar.setText("Ocultar Calendario");
                    calendar_view.setVisibility(View.VISIBLE);
                    aux=true;
                }
            }
        });*/
        btnCleanDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblDateNewTask.setText("Fecha: Sin Asignar");
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=localDB.getTasks();

                for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                    idMax= res.getInt(0);
                }
                idMax=idMax+1;
                if(priority!=0) {
                    Toast.makeText(context, "Insertando nuevo elemento", Toast.LENGTH_SHORT).show();
                    localDB.insertNewTask(idMax, txtTitleNewTask.getText().toString(), txtDescNewTask.getText().toString(), priority, date);
                }
                dialog.hide();
            }
        });

        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
