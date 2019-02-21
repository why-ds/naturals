package com.naturals.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="t_eafile")
@EqualsAndHashCode(of = "eafileno")
@ToString(exclude= {"electronicApproval"})
public class EAFile {
	@Id
	private String eafileno;
	private String eano;
	private String eafilnm;
	private String eafilernm;
	private String eafilesize;
	@CreationTimestamp
	private Timestamp insertdt;
	private String insertempno;
	@UpdateTimestamp
	private Timestamp updatedt;
	private String updateempno;
	

	@ManyToOne
	@JoinColumn(name="eano", insertable=false, updatable=false)
	private ElectronicApproval electronicApproval;


}
