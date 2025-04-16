package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.spring.model.Employee;
import com.demo.spring.service.EmpService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") 
public class EmpController {

	@Autowired
	private EmpService empService;

	// Get All Emps

	@GetMapping("/emps")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = empService.getEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	// Get Emp By id

	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = empService.getEmployee(id);

		if (employee != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}

	// Add emp

	@PostMapping("/emp")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee addEmployee = empService.addEmployee(employee);

		if (addEmployee != null) {
			return new ResponseEntity<Employee>(addEmployee, HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

	}

	// Update emp

	@PutMapping("/emp/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
	    employee.setId(id);
	    Employee updatedEmployee = empService.updateEmployee(employee);

	    if (updatedEmployee != null) {
	        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	    }
	}


	// delete emp

	@DeleteMapping("/emp/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		empService.deleteEmployee(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

	// search by name,phone,email
	@GetMapping("/emp/search/{keyword}")
	public ResponseEntity<List<Employee>> searchEmployee(@PathVariable String keyword) {
		List<Employee> searchEmployee = empService.searchEmployee(keyword, keyword, keyword);
		return new ResponseEntity<List<Employee>>(searchEmployee, HttpStatus.OK);
	}
}
