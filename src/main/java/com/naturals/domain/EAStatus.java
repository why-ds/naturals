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

@Getter
@Setter
@Entity
@Table(name="t_eastatus")
@EqualsAndHashCode(of = "eastatusno")
@ToString(exclude= {"electronicApproval"})
public class EAStatus {
	
	@Id
	private String eastatusno;
	private String eastatusnm;
	private String insertempno;
	@CreationTimestamp
	private Timestamp insertdt;
	private String updateempno;
	@UpdateTimestamp
	private Timestamp updatedt;

	@OneToOne
	@JoinColumn(name="eastatusno")
	private ElectronicApproval electronicApproval;
	
}
