package com.cj.constellation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConstellationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConstellationApplication.class, args);
	}

}
