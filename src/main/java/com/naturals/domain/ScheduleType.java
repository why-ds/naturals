package com.naturals.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="t_scheduletype")
@EqualsAndHashCode(of = "scheduleid")
@ToString(exclude= {"timeAttendance"})
public class ScheduleType {
	
	@Id
	private String scheduleid;
	private String schedulename;
	private String insertempno;
	@CreationTimestamp
	private Timestamp insertdt;
	private String updateempno;
	@UpdateTimestamp
	private Timestamp updatedt;
	
	@OneToMany
	@JoinColumn(name="scheduleid")
	private List<TimeAttendance> timeAttendance;
}