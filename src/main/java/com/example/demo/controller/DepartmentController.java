package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // your port localhost
@RequestMapping("/api/v1/")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// get all employees
	@GetMapping("/departments")
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}
	
	//get all employees count
	@GetMapping("/count_departments")
	public long getAllDepartmentsCount(){
		return departmentRepository.count();
	}
	
	//create employee rest api
	@PostMapping("/department")
	public Department createDepartment(@RequestBody Department department) {
		return departmentRepository.save(department);
	}
	
	//get employee by id rest api
	@GetMapping("/department/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));
		return ResponseEntity.ok(department);
	}
	
	//update employee rest api
	@PutMapping("/department/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id , @RequestBody Department departmentDetails) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));
		department.setName(departmentDetails.getName());
		
		Department updatedDepartment = departmentRepository.save(department);
		return ResponseEntity.ok(updatedDepartment);
	}
	
	//delete employee rest api
	@DeleteMapping("/department/{id}")
	public ResponseEntity <Map<String, Boolean>> deleteDepartment(@PathVariable Long id){
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));
		departmentRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
