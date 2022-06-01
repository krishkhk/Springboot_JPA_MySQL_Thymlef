package com.SpringBoot.restAPI.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.SpringBoot.restAPI.Repo.EmployeeRepo;
import com.SpringBoot.restAPI.model.Employee;

@Service
public class EmployeeServiceImple implements EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;

	public List<Employee> getAllEmployee() {
		// TODO AllDetails
		return empRepo.findAll();
	}

	public void saveEmployee(Employee employee) {
		// TODO Save

		this.empRepo.save(employee);

	}

	public Employee getEmployeById(Long id) {
		// TODO Get By Id
	
		 Optional< Employee> optional=empRepo.findById(id);
		 Employee emp=null;
		 if(optional.isPresent()) {
			 emp=optional.get();
		 }else {
			 throw new RuntimeException("Employee Id not Found :: " +id);
			 
		 }
		 
		return emp;
	}


	public void deleteEmployeById(Long id) {
		// TODO Delete Method
		
		this.empRepo.deleteById(id);
		
		
	}

	
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		 Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			  Sort.by(sortField).descending();
			 
			 PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
			 return this.empRepo.findAll(pageable);
	}

	
	

}
