package com.projectFinal.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MircoservicesGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MircoservicesGatewayApplication.class, args);
	}

}
