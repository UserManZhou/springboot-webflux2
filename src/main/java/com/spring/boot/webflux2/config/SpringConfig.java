package com.spring.boot.webflux2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
        useDefaultFilters = false,
        basePackages = "com.spring.boot.webflux2",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Component.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Service.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Repository.class)
        }
)
public class SpringConfig {


}
