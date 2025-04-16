package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.model.Employee;
import com.demo.spring.repo.EmpRepo;

@Service
public class EmpService {
	
	@Autowired
	private EmpRepo repo;

	public List<Employee> getEmployees() {
		return repo.findAll();
	}

	public Employee getEmployee(int id) {
		return repo.findById(id).orElse(null);
	}

	public Employee addEmployee(Employee employee) {
		return repo.save(employee);
	}

	public Employee updateEmployee(Employee employee) {
	    Optional<Employee> existingEmployee = repo.findById(employee.getId());

	    if (existingEmployee.isPresent()) {
	        return repo.save(employee); 
	    } else {
	        return null;
	    }
	}


	public void deleteEmployee(int id) {
		repo.deleteById(id);
	}

	public List<Employee> searchEmployee(String name, String phone, String email) {
		return repo.findByNameContainingOrPhoneContainingOrEmailContaining(name,phone,email);
	}		
}
