package com.spring.boot.webflux2.handler;

import com.spring.boot.webflux2.entity.User;
import com.spring.boot.webflux2.service.UserService;
import io.netty.channel.ChannelHandler;
import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.EntityResponse.fromObject;
@ChannelHandler.Sharable
public class UserHandler {


    private UserService userService;

    public UserHandler(UserService userService){
        this.userService = userService;
    }

    public Mono<ServerResponse> userId(ServerRequest serverRequest){
        int id = Integer.parseInt(serverRequest.pathVariable("id"));
        System.out.println(id+"\t"+"id");
        Mono<ServerResponse> responseMono = (Mono<ServerResponse>) ServerResponse.notFound().build();
        Mono<User> userById = this.userService.getUserById(id);
        return userById.flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(fromObject(userById))).switchIfEmpty(responseMono);
    }

    public Mono<ServerResponse> adduser(ServerRequest serverRequest){
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return ServerResponse.ok().build(this.userService.saveUser(userMono)) ;
    }

    public Mono<ServerResponse> findUser(ServerRequest serverRequest) {
        Flux<User> user = userService.findUser();
        return  ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user,User.class);
    }
}
