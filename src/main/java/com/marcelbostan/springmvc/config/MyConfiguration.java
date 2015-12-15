package com.marcelbostan.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Marcel Bostan on 12/14/2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.marcelbostan.springmvc")
public class MyConfiguration {

}
