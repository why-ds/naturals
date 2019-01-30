package com.naturals.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.naturals.domain.Employee;
import com.naturals.domain.EmployeeRole;

import lombok.Getter;
import lombok.extern.java.Log;

@SuppressWarnings("serial")
@Getter
@Log
public class TASecurityUser extends User {

	private static final String ROLE_PREFIX = "ROLE_";

	private Employee employee;
	
	private String employeeStr;

	public TASecurityUser(Employee employee) {
		
		super(employee.getEmpno(), employee.getPassword(), makeGrantedAuthority(employee.getRoles()));

		this.employee = employee;

		this.employeeStr = employee.getEmpno();
		
		log.info("TASecurityUser END");
	}

	private static List<GrantedAuthority> makeGrantedAuthority(List<EmployeeRole> roles) {

		log.info("makeGrantedAuthority  START");
		
		List<GrantedAuthority> list = new ArrayList<>();

		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRolenm())));
		
		log.info("makeGrantedAuthority  END");

		return list;
	}

}