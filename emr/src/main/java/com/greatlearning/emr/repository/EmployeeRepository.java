package com.greatlearning.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.emr.entity.Employee;

public interface EmployeeRepository 
  extends JpaRepository<Employee, Integer> {

}          
