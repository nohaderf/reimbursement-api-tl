package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReimbursementRequestApplication {

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(ReimbursementRequestApplication.class);
		SpringApplication.run(ReimbursementRequestApplication.class, args);
//		logger.warn("employee id: {}", employeeRepository.getById(1));
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			for (Employee e : employeeRepository.findAll()) {
//				System.out.println(e);
//			}
//		};
//	}
}
