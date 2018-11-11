package com.example.alan_.agendatec.Model.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLInput;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String tasksdb="tasksdb.db";
    private Context context;
    private TableTasks tasks = new TableTasks();
    private QueryHelper queryHelper = new QueryHelper();
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context){
        super(context, tasksdb, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tasks.getStringQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tasks.getTableName());
        onCreate(db);
    }

    public void truncateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="";
        query="DELETE FROM "+tasks.getTableName()+";";
        db.execSQL(query);
        db.execSQL("VACUUM;");
    }

    public String insertNewTask(int id, String title, String text, byte priority, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        /*Cursor res=db.rawQuery(queryHelper.verifyMaxID(id), null);
        if(res.moveToFirst()){
            Log.i(TAG, "Ya se encuentra");
            return "0";
        }else{
            Log.i(TAG, "No se encuentra");*/
            ContentValues contentValues = new ContentValues();
            contentValues.put(tasks.getId(), id);
            contentValues.put(tasks.getTitle(), title);
            contentValues.put(tasks.getText(), text);
            contentValues.put(tasks.getPriority(), priority);
            contentValues.put(tasks.getDate(), date);

            long idTask=db.insert(tasks.getTableName(), null, contentValues);
            if (idTask<=0){
                return "La tarea no se ha podido registrar";
            }else{
                return "Tarea Registrada Correctamente";
            }
        //}
    }

    public void insertElements(){
        insertNewTask(1, "Titulo de tarea 1", "Descripcion de la tarea 1", (byte) 1, "12/11/2018");
        insertNewTask(2, "Titulo de tarea 2", "Descripcion de la tarea 2", (byte) 3, "12/11/2018");
        insertNewTask(3, "Titulo de tarea 3", "Descripcion de la tarea 3", (byte) 2, "16/11/2018");
    }

    public Cursor getTasks(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(queryHelper.getAllTasks(), null);
    }
}
