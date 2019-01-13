package com.naturals.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 출결관리 20190111 윤동섭 
*/

@Getter
@Setter
@Entity
@Table(name="t_timeattendance")
@EqualsAndHashCode(of = "tno")
@ToString(exclude="employee")
public class TimeAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tno;
	private String deptno;
	private String positonno;
	private String empno;
	private String gymnastics;
	private String unchk;
	private String dayoff;
	private String halfdayoff;
	private String education;
	private String businesstrip;
	private String outsidebusiness;
	private String late;
	private String leaveearly;
	private String goingout;
	private String leaveofabsence;
	private String memo;
	private Timestamp starttime;
	private Timestamp endtime;
	@CreationTimestamp
	private Timestamp insertdt;
	@UpdateTimestamp
	private Timestamp updatedt;
	
	@ManyToMany
	@JoinColumn(name="empno")
	private List<Employee> employee;
	
}
