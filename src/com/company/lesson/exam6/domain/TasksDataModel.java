package com.company.lesson.exam6.domain;

import java.util.List;

public class TasksDataModel {
    private List<Task> taskList;

    public TasksDataModel(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task){
        taskList.add(task);
    }
}
