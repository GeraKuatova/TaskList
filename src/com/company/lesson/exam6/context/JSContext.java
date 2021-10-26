package com.company.lesson.exam6.context;

import com.company.lesson.exam6.domain.Task;
import com.company.lesson.exam6.domain.TasksDataModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class JSContext implements DataContext{
    private final Gson gson;
    private final String path = "data/json/tasks.json";
    public JSContext() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public boolean add(TasksDataModel tasksDataModel) {
        try(FileReader fileReader = new FileReader(path)){
            FileWriter writer = new FileWriter(path);
            gson.toJson(tasksDataModel, writer);
            writer.flush();
            writer.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Task> getTask() {
        try(FileReader fileReader = new FileReader(path)){
            Optional<Task> taskList = gson.fromJson(fileReader, new TypeToken<List<Task>>(){}.getType());
            return taskList;
        }catch (IOException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
