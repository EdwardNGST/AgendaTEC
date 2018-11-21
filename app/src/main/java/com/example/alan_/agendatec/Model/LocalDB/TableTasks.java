package com.example.alan_.agendatec.Model.LocalDB;

/***********************
 * ESTA CLASE PRETENDE GUARDAR LA ESTRUCTURA DE NUESTRA BASE DE DATOS CON EL FIN DE AL MANDAR A LLAMAR
 * CUALQUIER COSA DE LA BASE DE DATOS NO TENER QUE RECORDAR EL NOMBRE DE CADA ELEMENTO
 */
public class TableTasks {
    private String tableName;//Nombre de la tabla

    private String id;//Id de la tarea
    private String title;//Titulo de la tarea
    private String text;//Texto
    private String priority;//Prioridad (Urgente/Importante/Normal)
    private String date;//Fecha en la que se necesita recordar esta tarea

    private String stringQuery;

    public TableTasks(){
        tableName="tasks";

        id="idTask";
        title="titleTask";
        text="textTask";
        priority="priorityTask";
        date="dateTask";

        //Esta variable guarda la cadena completa para el create de la tabla de tareas
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
