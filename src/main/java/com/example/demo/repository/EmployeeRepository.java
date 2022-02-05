package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("SELECT e , d from Employee e join e.department d")
	List<Employee> GetEmployeesWithDepName();
	
	@Query("SELECT e.firstname,e.lastname,e.email,d.name from Employee e join e.department d where e.id=:id")
    String GetEmployeeWithDep(@Param("id") Long identif);
}
