package com.naturals.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name="t_employee")
@EqualsAndHashCode(of = "empno")
@ToString(exclude= {"position", "department"})
public class Employee {
	@Id
	private String empno;
	private String empnm;
	private String positionno;
	private String deptno;
	private Timestamp insertempno;
	@CreationTimestamp
	private Timestamp insertdt;
	private Timestamp updateempno;
	@UpdateTimestamp
	private Timestamp updatedt;

	@OneToOne()
	@JoinColumn(name="positionno", insertable=false, updatable=false)
	private Position position;

	@OneToOne()
	@JoinColumn(name="deptno", insertable=false, updatable=false)
	private Department department;
}
