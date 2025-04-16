package com.demo.spring.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.model.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer>{

	List<Employee> findByNameContainingOrPhoneContainingOrEmailContaining(String name, String phone, String email);
}
