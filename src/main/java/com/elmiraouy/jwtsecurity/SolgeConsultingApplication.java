package com.elmiraouy.jwtsecurity;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class SolgeConsultingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolgeConsultingApplication.class, args);
		System.out.println("class *************");
	}


}
