package com.example.alan_.agendatec.Model.LocalDB;

public class TableTasks {
    private String tableName;

    private String id;
    private String title;
    private String text;
    private String priority;
    private String date;

    private String stringQuery;

    public TableTasks(){
        tableName="tasks";

        id="idTask";
        title="titleTask";
        text="textTask";
        priority="priorityTask";
        date="dateTask";

        stringQuery="CREATE TABLE "+tableName+"("+
                id+" NUMBER PRIMARY KEY,"+
                title+" TEXT, "+
                text+" TEXT, "+
                priority+" NUMBER, "+
                date+" TEXT);";
    }

    public String getTableName() {
        return tableName;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public String getStringQuery() {
        return stringQuery;
    }
}
