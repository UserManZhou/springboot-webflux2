package com.spring.boot.webflux2.router;

import com.mysql.fabric.Server;
import com.spring.boot.webflux2.handler.UserHandler;
import com.spring.boot.webflux2.service.impl.UserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;


import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

public class ServerRouter {
    public static void main(String[] args) throws IOException {
        ServerRouter serverRouter = new ServerRouter();
        serverRouter.createRouterServer();
        System.out.println("exit");
        System.in.read();
    }
    // 配置路由
    public RouterFunction<ServerResponse> function(){
        UserHandler userHandler = new UserHandler(new UserServiceImpl());
        return RouterFunctions.route(
                GET("/users/{id}").and(accept(APPLICATION_JSON)) , userHandler::userId)
                .andRoute(GET("/users/").and(accept(APPLICATION_JSON)),userHandler::adduser)
                .andRoute(GET("/users/findUser").and(accept(APPLICATION_JSON)),userHandler::findUser);
    }
    // 适配器
    public void createRouterServer(){
        RouterFunction<ServerResponse> function = function();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(function);
        ReactorHttpHandlerAdapter reactorHttpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);
        // 创建服务
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(reactorHttpHandlerAdapter).bindNow();
    }



}
