package com.naturals.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.Department;

public interface DepartmentRepository  extends CrudRepository<Department, Long>, QuerydslPredicateExecutor<Department>{

}
