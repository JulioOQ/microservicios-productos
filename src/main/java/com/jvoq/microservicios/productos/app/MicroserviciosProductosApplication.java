package com.jvoq.microservicios.productos.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviciosProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosProductosApplication.class, args);
	}

}
