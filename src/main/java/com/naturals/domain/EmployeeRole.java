package com.naturals.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="t_employee_role")
@EqualsAndHashCode(of = "username")
@ToString
public class EmployeeRole {
	@Id
	private String username;
	private String rolenm;
}
