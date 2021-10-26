package com.company.lesson.exam6.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper <T> {
    private final String path = "data/json/tasks.json";
        private Gson gson;

        public JsonHelper() {
            this.gson = new Gson();
        }

        public  void writer(String path, T data) throws IOException {
            FileWriter writer = new FileWriter(path);
            String serializer = gson.toJson(data);
            writer.write(serializer);
            writer.close();
        }

        public  TasksDataModel readTasks(String path) throws IOException {
            FileReader reader = new FileReader(path);
            TasksDataModel tasksDataModel = gson.fromJson(reader, TasksDataModel.class);
            reader.close();
            return tasksDataModel;
        }

    public boolean add(Task task) {
        try(FileReader fileReader = new FileReader(path)){
            List<Task> taskList = gson.fromJson(fileReader, new TypeToken<List<Task>>(){}.getType());
            if(taskList == null){
                taskList = new ArrayList<>();
            }
            taskList.add(task);
            FileWriter writer = new FileWriter(path);
            gson.toJson(taskList, writer);
            writer.flush();
            writer.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

}

