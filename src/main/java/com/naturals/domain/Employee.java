package com.naturals.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 사원정보 20190111 윤동섭 
*/

@Getter
@Setter
@Entity
@Table(name="t_employee", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@EqualsAndHashCode(of = "username")
@ToString(exclude= {"position", "department", "timeAttendance", "roles"})
public class Employee {
	
	@Id
	private String username;
	private String empno;
	private String empnm;
	private String password;
	private String deptno;
	private String positionno;
	private String email;
	@Column(columnDefinition="varchar(255) default 1") 
	private String enabled;
	private String pwFlag;
	private String insertempno;
	@CreationTimestamp
	private Timestamp insertdt;
	private String updateempno;
	@UpdateTimestamp
	private Timestamp updatedt;

	@OneToOne()
	@JoinColumn(name="positionno", insertable=false, updatable=false)
	private Position position;

	@OneToOne()
	@JoinColumn(name="deptno", insertable=false, updatable=false)
	private Department department;
	
	
	@OneToMany
	@JoinColumn(name="username")
	private List<TimeAttendance> timeAttendance;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="employee")
	private List<EmployeeRole> roles;
	
}
