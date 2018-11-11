package com.example.alan_.agendatec.Model.LocalDB;

public class QueryHelper {
    private TableTasks tasks = new TableTasks();

    public String searchTaskByID(int id){
        return "SELECT "+tasks.getId()+" FROM "+tasks.getTableName()+" WHERE "+tasks.getId()+"="+id+";";
    }

    public String getAllTasks(){
        return "SELECT "+tasks.getId()+", "+tasks.getTitle()+", "+tasks.getText()+", "+tasks.getPriority()+", "+tasks.getDate()+" FROM "+tasks.getTableName()+";";
    }

    public String searchTaskByTitle(String title){
        return "SELECT "+tasks.getId()+" FROM "+tasks.getTableName()+" WHERE "+tasks.getTitle()+"='"+title+"';";
    }

    public String searchTaskByPriority(byte priority){
        return "SELECT "+tasks.getId()+" FROM "+tasks.getTableName()+" WHERE "+tasks.getPriority()+"="+priority+";";
    }

    public String verifyMaxID(int id){
        return "SELECT MAX("+tasks.getId()+") FROM "+tasks.getTableName()+" WHERE "+tasks.getId()+" = "+id+";";
    }
}
