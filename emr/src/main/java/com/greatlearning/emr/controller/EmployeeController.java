package com.greatlearning.emr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.emr.entity.Employee;
import com.greatlearning.emr.service.EmployeeService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;    

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/list")
	public String listemployees(Model theModel) {

		List<Employee> employees = employeeService.findAll();

		theModel.addAttribute("employees", employees);

		return "list-employees";
	}

	@RequestMapping("/displayEmployeeForm")
	public String addStudent_Step1(Model theModel) {

		Employee employee = new Employee();

		theModel.addAttribute("employee", employee);

		return "employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
	    @RequestParam("lastName") String lastName, @RequestParam("course") String email) {

	  employeeService.saveOrUpdate(id, firstName, lastName, email);

	  // use a redirect to 'employees-listing'
	  return "redirect:/employees/list";
	}  

	@RequestMapping("/displayEmployeeForm_Update")
	public String updateEmployee_Step1(@RequestParam("employeeId") int employeeId, Model theModel) {

		Employee employee = employeeService.findById(employeeId);

		// set 'employee' as a model attribute to pre-populate the form
		theModel.addAttribute("employee", employee);

		// send over to our form
		return "employee-form";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int employeeId) {

	    // delete the employee
	    employeeService.deleteById(employeeId);

	    // redirect to 'employees-listing'
	    return "redirect:/employees/list";
	}  

	@RequestMapping(value = "/403")
	public ModelAndView handleAccessDeniedError(Principal user) {

	  ModelAndView model = new ModelAndView();

	  if (user != null) {
	    model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
	  } else {
	    model.addObject("msg", "You do not have permission to access this page!");
	  }

	  model.setViewName("authorization-error-403");
	  return model;
	}  
	
}