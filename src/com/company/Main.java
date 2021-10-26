package com.company;

import com.company.lesson.exam6.context.DataContext;
import com.company.lesson.exam6.context.JSContext;
import com.company.lesson.server.Server;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        DataContext dataContext = new JSContext();
        try {
            new Server("localhost", 8001, dataContext).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
