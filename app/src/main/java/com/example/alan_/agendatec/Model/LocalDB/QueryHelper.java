package com.example.alan_.agendatec.Model.LocalDB;

public class QueryHelper {
    private TableTasks tasks = new TableTasks();

    //Guarda la cadena que buscara una tarea por su ID
    public String searchTaskByID(int id){
        return "SELECT "+tasks.getId()+" FROM "+tasks.getTableName()+" WHERE "+tasks.getId()+"="+id+";";
    }
    //Este metodo obtiene todas las tareas de la tabla
    public String getAllTasks(){
        return "SELECT "+tasks.getId()+", "+tasks.getTitle()+", "+tasks.getText()+", "+tasks.getPriority()+", "+tasks.getDate()+" FROM "+tasks.getTableName()+";";
    }
    //Este metodo busca tareas por titulo
    public String searchTaskByTitle(String title){
        return "SELECT "+tasks.getId()+" FROM "+tasks.getTableName()+" WHERE "+tasks.getTitle()+"='"+title+"';";
    }
    //Este metodo bus
    public String searchTaskByPriority(byte priority){
        return "SELECT "+tasks.getId()+" FROM "+tasks.getTableName()+" WHERE "+tasks.getPriority()+"="+priority+";";
    }
    public String verifyMaxID(int id){
        return "SELECT MAX("+tasks.getId()+") FROM "+tasks.getTableName()+" WHERE "+tasks.getId()+" = "+id+";";
    }
}
