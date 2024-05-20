package com.greatlearning.emr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.emr.entity.Employee;
import com.greatlearning.emr.repository.EmployeeRepository;
import com.greatlearning.emr.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {

		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public Employee findById(int employeeId) {
		return employeeRepository.findById(employeeId).get();
	}

	@Override
	public void saveOrUpdate(int id, String firstName, String lastName, String email) {

		System.out.println("Employee ID ->" + id);

		Employee employeeObj = null;
		if (id == 0) {

			employeeObj = new Employee(firstName, lastName, email);
			System.out.println("Add Employee Scenario");
		} else {

			System.out.println("Update Employee Scenario");

			employeeObj = this.findById(id);
			employeeObj.setFirstName(firstName);
			employeeObj.setLastName(lastName);
			employeeObj.setEmail(email);

		}

		// Save/Update the employeeObj
		this.save(employeeObj);

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}