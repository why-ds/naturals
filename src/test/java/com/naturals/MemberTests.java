package com.naturals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.naturals.persistence.EmployeeRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTests {
	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	/*
	 * @Test public void testInsert() {
	 * 
	 * for (int i = 0; i <= 100; i++) {
	 * 
	 * Employee employee = new Employee(); employee.setEmpno("user" + i);
	 * employee.setUpw("pw" + i); employee.setUname("사용자" + i);
	 * 
	 * EmployeeRole role = new EmployeeRole(); if (i <= 80) {
	 * role.setRoleName("BASIC"); } else if (i <= 90) { role.setRoleName("MANAGER");
	 * } else { role.setRoleName("ADMIN"); } employee.setRoles(Arrays.asList(role));
	 * 
	 * repo.save(employee); }
	 * 
	 * }
	 */
}
