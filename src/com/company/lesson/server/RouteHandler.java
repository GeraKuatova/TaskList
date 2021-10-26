package com.company.lesson.server;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

@FunctionalInterface
public interface RouteHandler {
    void handle(HttpExchange exchange) throws IOException;
}
