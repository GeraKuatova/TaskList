package com.company.lesson.exam6.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Task {
    private String id;
    private String title;
    private String executor;
    private String startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    private String endDate;
    private boolean done;
    private String description;


    public Task(String title, String executor, String description) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.executor = executor;
        this.description = description;
        endDate = "In Process";

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
