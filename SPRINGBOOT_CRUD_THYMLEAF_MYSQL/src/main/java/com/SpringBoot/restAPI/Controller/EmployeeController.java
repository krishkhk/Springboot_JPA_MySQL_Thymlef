package com.SpringBoot.restAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SpringBoot.restAPI.Service.EmployeeService;
import com.SpringBoot.restAPI.model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empServ;

	// Display List of Employees

	@GetMapping("/")
	public String viewHomePage(Model model) {

		model.addAttribute("listEmployees", empServ.getAllEmployee());

		 return findPaginated(1, "firstName", "asc", model);

	}

	// new Employee Form.

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		return "new_employee";

	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employe) {
		// save employee to database

		((EmployeeService) empServ).saveEmployee(employe);

		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {

		// get employee from the service
		Employee employee=empServ.getEmployeById(id);
		
		model.addAttribute("employee",employee);
	
		return "update_employee";

	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value="id") Long id) {
		
		this.empServ.deleteEmployeById(id);
		
		return "redirect:/";
		
		
	}
	
	//pagenation.
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
	    @RequestParam("sortField") String sortField,
	    @RequestParam("sortDir") String sortDir,
	    Model model) {
	    int pageSize = 10;

	    Page < Employee > page = empServ.findPaginated(pageNo, pageSize, sortField, sortDir);
	    List < Employee > listEmployees = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());

	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	    model.addAttribute("listEmployees", listEmployees);
	    return "index";
	}

}
