package com.naturals.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@ToString(exclude= {"employee","position","department", "tatype", "electronicApproval"})
public class TimeAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tno;
	private String deptno;
	private String positionno;
	private String empno;
	private String tatypeno;
	private String memo;
	private String taday;
	private String starttime;
	private String endtime;
	private String insertempno;
	@CreationTimestamp
	private Timestamp insertdt;
	private String updateempno;
	@UpdateTimestamp
	private Timestamp updatedt;
	
	@ManyToOne
	@JoinColumn(name="empno", insertable=false, updatable=false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="deptno", insertable=false, updatable=false)
	private Department department;

	@ManyToOne
	@JoinColumn(name="positionno", insertable=false, updatable=false)
	private Position position;

	@ManyToOne
	@JoinColumn(name="tatypeno", insertable=false, updatable=false)
	private TAType tatype;
	
//	@OneToMany
//	@JoinColumn(name="tno")
//	private List<ElectronicApproval> electronicApproval;
	
}
