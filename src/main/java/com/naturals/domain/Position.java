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
 * 직급정보 20190111 윤동섭 
*/

@Getter
@Setter
@Entity
@Table(name="t_position")
@EqualsAndHashCode(of = "positionno")
@ToString(exclude="employee")
public class Position {
	@Id
	private String positionno;
	private String positionnm;
	private Timestamp insertemp;
	@CreationTimestamp
	private Timestamp insertdt;
	private Timestamp updateemp;
	@UpdateTimestamp
	private Timestamp updatedt;
	
	@OneToOne
	private Employee employee;
	
}
