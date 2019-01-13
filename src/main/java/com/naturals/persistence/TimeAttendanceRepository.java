package com.naturals.persistence;

import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.TimeAttendance;

public interface TimeAttendanceRepository extends CrudRepository<TimeAttendance, Long>{
	
}
