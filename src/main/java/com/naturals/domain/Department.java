package com.naturals.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 부서정보 20190111 윤동섭 
*/

@Getter
@Setter
@Entity
@Table(name="t_department")
@EqualsAndHashCode(of = "deptno")
@ToString(exclude="employee")
public class Department {
	@Id
	private String deptno;
	private String deptnm;
	private Timestamp insertemp;
	@CreationTimestamp
	private Timestamp insertdt;
	private Timestamp updateemp;
	@UpdateTimestamp
	private Timestamp updatedt;

	@OneToOne
	private Employee employee;
	
}
