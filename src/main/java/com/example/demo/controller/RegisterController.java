package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class RegisterController {
	
	@Autowired
	private UserRepository userRepository;

	//create employee rest api
		@PostMapping("/register")
		public User registerUser(@RequestBody User user) throws Exception {
			String templEmail = user.getEmail();
			if(templEmail !=null && !"".equals(templEmail)) {
				User userObj = userRepository.findByEmail(templEmail);
				if(userObj != null) {
					throw new Exception("User with " + templEmail + " is already exist");
				}
			}
			User userObj = null;
			userObj = userRepository.save(user);
			return userObj;
		}
		
		
		@PostMapping("/login")
		public User loginUser(@RequestBody User user) throws Exception {
			String tempEmail = user.getEmail();
			String tempPassword = user.getPassword();
			User userObj = null;
			if(tempEmail !=null && tempPassword !=null) {
				userObj = userRepository.findByEmailAndPassword(tempEmail, tempPassword);
			}
			if (userObj == null) {
				throw new Exception("Error to login");
			}
			return userObj;
		}
		
		// get all employees
		@GetMapping("/users")
		public List<User> getAllUsers(){
			return userRepository.findAll();
		}
				
		//get employee by id rest api
		@GetMapping("/user/{id}")
		public ResponseEntity<User> getUserById(@PathVariable Long id) {
			User user = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
			return ResponseEntity.ok(user);
		}
		
		//update employee info rest api
		@PutMapping("/userProfile/{id}")
		public ResponseEntity<User> updateUserInfo(@PathVariable Long id , @RequestBody User userDetails) {
			User user = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
			user.setUsername(userDetails.getUsername());
			user.setEmail(userDetails.getEmail());
			user.setPassword(userDetails.getPassword());
			
			User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);
		}
		
		//update employee pass rest api
		@PutMapping("/userPassword/{id}")
		public ResponseEntity<User> updateUserPass(@PathVariable Long id , @RequestBody User userDetails) {
			User user = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
			user.setPassword(userDetails.getPassword());
			
			User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);
		}
		
}
