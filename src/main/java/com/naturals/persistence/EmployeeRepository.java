package com.naturals.persistence;

import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{
	
}
