package com.naturals.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name="t_electronicapproval")
@EqualsAndHashCode(of = "eano")
@ToString(exclude= {"eaStatus", "timeAttendance", "eaFile"})
public class ElectronicApproval {
	@Id
	private String eano;
	private String eastatusno;
	private Long tno;
	private String starttime_b;
	private String starttime_a;
	private String endtime_b;
	private String endtime_a;
	private String tatypeno_b;
	private String tatypeno_a;
	private String insertempno;
	@CreationTimestamp
	private Timestamp insertdt;
	private String updateempno;
	@UpdateTimestamp
	private Timestamp updatedt;
	private String memo;

	@OneToOne
	@JoinColumn(name="eastatusno", insertable=false, updatable=false)
	private EAStatus eaStatus;

	@ManyToOne
	@JoinColumn(name="tno", insertable=false, updatable=false)
	private TimeAttendance timeAttendance;
	
	@OneToMany
	@JoinColumn(name="eano")
	private List<EAFile> eaFile;
}
