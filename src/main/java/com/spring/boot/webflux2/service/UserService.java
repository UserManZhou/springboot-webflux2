package com.spring.boot.webflux2.service;

import com.spring.boot.webflux2.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> getUserById(int id);

    Flux<User> findUser();

    Mono<Void> saveUser(Mono<User> userMono);

}
