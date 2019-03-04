package com.naturals.persistence;

import java.time.LocalDate;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.QTimeAttendance;
import com.naturals.domain.TimeAttendance;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;


public interface TimeAttendanceRepository extends CrudRepository<TimeAttendance, Long>, QuerydslPredicateExecutor<TimeAttendance>{

	public default Predicate makePredicate(String type, String keyword, String sdate, String edate,String empno, String hFlag) {
		   
		BooleanBuilder builder = new BooleanBuilder();
		QTimeAttendance timeAttendance = QTimeAttendance.timeAttendance;

		LocalDate date = LocalDate.now();
		String maxDay = String.valueOf(date.withDayOfMonth(date.lengthOfMonth()));
		String minDay= String.valueOf(date.withDayOfMonth(01));
		
		builder.and(timeAttendance.tno.gt(0));
		  
		builder.and(timeAttendance.empno.eq(empno));
		
		if(hFlag != null) {
			builder.and(timeAttendance.taday.between(minDay, maxDay));
			return builder;
		}
		
		if((sdate == null && edate == null) || (sdate == "" && edate == "")) {
		}else if((sdate != null && edate == null) || (sdate != "" && edate == "")){
			builder.and(timeAttendance.taday.gt(sdate));
		}else if((sdate != null && edate != null) || (sdate != "" && edate != "")) {
			builder.and(timeAttendance.taday.between(sdate, edate));
		}
				
		if(type != null && type != "") {
			builder.and(timeAttendance.tatypeno.eq(type));
		}		
		  
		return builder;
	}
}
