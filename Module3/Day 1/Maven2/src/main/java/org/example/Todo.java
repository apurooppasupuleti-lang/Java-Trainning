package org.example;

public class Todo {
    private int id;
    private String task;
    private boolean isCompleted;

    public Todo(){

    }
    public Todo(String task, boolean isCompleted){
        this.task = task;
        this.isCompleted = isCompleted;
    }
    public Todo(int id, String task, boolean isCompleted){
        this.id = id;
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString(){
        return "[Todo - id: " +  this.id + ", task: " +  this.task + ", isCompleted: " +  this.isCompleted + "]";
    }
}
