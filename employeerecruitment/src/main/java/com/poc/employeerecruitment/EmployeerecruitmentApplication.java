package com.poc.employeerecruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeerecruitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeerecruitmentApplication.class, args);
	}

}
