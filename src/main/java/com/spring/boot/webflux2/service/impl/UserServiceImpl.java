package com.spring.boot.webflux2.service.impl;

import com.spring.boot.webflux2.entity.User;
import com.spring.boot.webflux2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository(value = "UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Mono<User> getUserById(int id) {
        String sql = "SELECT * FROM `user` WHERE `id` = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
        return Mono.justOrEmpty(user);
    }

    @Override
    public Flux<User> findUser() {
        String sql = "SELECT * FROM `user`";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        System.out.println(users);
        return Flux.fromIterable(users);
    }

    @Override
    public Mono<Void> saveUser(Mono<User> userMono) {
        String sql = "INSERT INTO `user`(`name`,`money`) VALUES (?,?)";
        return  userMono.doOnNext(user -> {
            jdbcTemplate.update(sql, user.getName(),user.getMoney());
        }).thenEmpty(Mono.empty());
    }
}
