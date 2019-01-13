package com.naturals.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.QTimeAttendance;
import com.naturals.domain.TimeAttendance;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface TimeAttendanceRepository extends CrudRepository<TimeAttendance, Long>, QuerydslPredicateExecutor<TimeAttendance>{
	
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QTimeAttendance timeAttendance = QTimeAttendance.timeAttendance;
		builder.and(timeAttendance.tno.gt(0));
		
		if(type==null) {
			return builder;
		}
		
		switch(type) {
		case "dept":
			builder.and(timeAttendance.deptno.like("%" + keyword + "%"));
			break;
		case "empnm":
			builder.and(timeAttendance.employee.empnm.like("%"+ keyword + "%"));
			break;
		case "empno":
			builder.and(timeAttendance.empno.like("%" + keyword + "%"));
			break;
		case "memo":
			builder.and(timeAttendance.memo.like("%" + keyword + "%"));
		}
		
		return builder;
	}
	
}
