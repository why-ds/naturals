package com.naturals.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 직급정보 20190111 윤동섭 
*/

@Getter
@Setter
@Entity
@Table(name="t_position")
@EqualsAndHashCode(of = "positionno")
@ToString(exclude= {"employee","timeAttendance"})
public class Position {
	@Id
	private String positionno;
	private String positionnm;
	private String insertempno;
	@CreationTimestamp
	private Timestamp insertdt;
	private String updateempno;
	@UpdateTimestamp
	private Timestamp updatedt;
	
	@OneToOne
	@JoinColumn(name="positionno")
	private Employee employee;

	@OneToMany
	@JoinColumn(name="positionno")
	private List<TimeAttendance> timeAttendance;
	
}
