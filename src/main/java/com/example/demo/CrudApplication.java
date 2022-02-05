package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class CrudApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
	
	/*@Bean
    public CommandLineRunner demo(EmployeeRepository employeeRepository) {
        return (args) -> {
        	Department d1 = new Department("computer sience");
    		Employee e1 = new Employee("exemple", "exp", "exp@gmail.com", d1);
            employeeRepository.save(e1);
            employeeRepository.findAll();
        };
    }*/

}
