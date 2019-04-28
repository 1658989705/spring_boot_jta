package com.jok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import com.jok.config.DBCarConfig;
import com.jok.config.DBProductConfig;


@SpringBootApplication
@ComponentScan(basePackages = {"com.jok.controller","com.jok.car.service","com.jok.product.service","com.jok.config"})
@EnableAutoConfiguration
@EnableConfigurationProperties(value= {DBCarConfig.class,DBProductConfig.class})
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
