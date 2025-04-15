package com.sistemadegestaodeprojetos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.sistemadegestaodeprojetos.model")
public class SistemadegestaodeprojetosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemadegestaodeprojetosApplication.class, args);
	}

};
