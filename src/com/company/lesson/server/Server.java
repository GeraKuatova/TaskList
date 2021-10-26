package com.company.lesson.server;

import com.company.Utils;
import com.company.lesson.exam6.context.DataContext;
import com.company.lesson.exam6.context.JSContext;
import com.company.lesson.exam6.domain.*;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.*;

public class Server extends BasicServer{
    private JSContext context;
    private final DataContext dataContext;
    private TasksDataModel tdm;
    private final JsonHelper<TasksDataModel> jsonHelperTasks = new JsonHelper<>();

    public Server(String host, int port, DataContext dataContext) throws IOException {
        super(host, port);
        this.context = new JSContext();
        this.dataContext = dataContext;
        registerGet("/", this::rootHandler);
        registerGet("/index", this::rootHandler);
        registerPost("/addTask", this::rootPostHandler);
        registerGet("/deleteTask", this::deleteHandler);
        registerGet("/fulfilTask", this::fulfilHandler);

        tdm = jsonHelperTasks.readTasks("data/json/tasks.json");
    }

    private void fulfilHandler(HttpExchange exchange) {
        String queryParams = getQueryParams(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(queryParams, "&");
        String id = params.get("id");
        Task task = tdm.getTaskList()
                .stream()
                .filter(t -> t.getId().equals(id))
                .findAny()
                .orElse(null);

        task.setDone(true);
        context.add(tdm);
        redirect303(exchange, "/");
    }

    private void deleteHandler(HttpExchange exchange) {
        String queryParams = getQueryParams(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(queryParams, "&");
        String id = params.get("id");
        Task task = tdm.getTaskList()
                    .stream()
                    .filter(t -> t.getId().equals(id))
                    .findAny()
                    .orElse(null);
        tdm.getTaskList().remove(task);
        context.add(tdm);
        redirect303(exchange, "/");
    }

    private void rootHandler(HttpExchange exchange) throws IOException {
        String queryParams = getQueryParams(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(queryParams, "&");
        String id = params.get("id");
        Task task = null;
        if (id != null){
            task = tdm.getTaskList()
                    .stream()
                    .filter(t -> t.getId().equals(id))
                    .findAny()
                    .orElse(null);
        }
        if (task == null){
            renderTemplate(exchange, "index.html", tdm);
        }else {
            renderTemplate(exchange, "task.html", task);
        }
    }

    private void rootPostHandler(HttpExchange exchange) {
        String body = getBody(exchange);
        Map<String, String> content = Utils.parseUrlEncoded(body, "&");
        String title = content.get("title");
        String description = content.get("description");
        String executor = content.get("executor");
        Optional<Task> optTask = Optional.of(new Task(title, executor, description));
        if(optTask.isPresent()){
            Task task = optTask.get();
            tdm.add(task);
            context.add(tdm);
        }
        redirect303(exchange, "/");
    }

}
