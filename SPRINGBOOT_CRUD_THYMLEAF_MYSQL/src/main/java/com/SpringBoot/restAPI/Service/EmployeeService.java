package com.SpringBoot.restAPI.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.SpringBoot.restAPI.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployee();
	void saveEmployee(Employee employe);
	Employee getEmployeById(Long id);
	
	void deleteEmployeById(Long id);
	
	Page < Employee > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    
	
}
