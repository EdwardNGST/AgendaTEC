package com.example.alan_.agendatec.Model.Class;

public class Tasks {
    private int id;
    private String title;
    private String text;
    private byte priority;
    private String date;

    public Tasks(int id, String title, String text, byte priority, String date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.priority = priority;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
