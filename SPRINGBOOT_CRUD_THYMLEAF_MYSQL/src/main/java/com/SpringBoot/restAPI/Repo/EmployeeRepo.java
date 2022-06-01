package com.SpringBoot.restAPI.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.restAPI.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	

}
