package com.naturals.persistence;

import java.time.LocalDate;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.ElectronicApproval;
import com.naturals.domain.QElectronicApproval;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface ElectronicApprovalRepository extends CrudRepository<ElectronicApproval, Long> , QuerydslPredicateExecutor<ElectronicApproval>{
//ADMIN용
	public default Predicate makePredicate(String type, String keyword, String empno,  String sdate, String edate, String iFlag) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QElectronicApproval electronicApproval = QElectronicApproval.electronicApproval;
		
			LocalDate date = LocalDate.now();
			String maxDay = String.valueOf(date.withDayOfMonth(date.lengthOfMonth()));
			String minDay= String.valueOf(date.withDayOfMonth(01));
			
//			builder.and(electronicApproval.tno.gt(0));
			
			  
			builder.and(electronicApproval.timeAttendance.empno.eq(empno));

			if(iFlag != null) {
				builder.and(electronicApproval.timeAttendance.taday.between(minDay, maxDay));
				builder.and(electronicApproval.eastatusno.eq(Long.valueOf(1)));
				return builder;
			}
			
			if((sdate == null && edate == null) || (sdate == "" && edate == "")) {
			}else if((sdate != null && edate == null) || (sdate != "" && edate == "")){
				builder.and(electronicApproval.timeAttendance.taday.goe(sdate));
			}else if((sdate != null && edate != null) || (sdate != "" && edate != "")) {
				builder.and(electronicApproval.timeAttendance.taday.between(sdate, edate));
			}
					
			if(type != null && type != "") {
				builder.and(electronicApproval.eastatusno.eq(Long.valueOf(type)));
			}
			    
		return builder;
	}
	
	
// USER용
	public default Predicate makePredicate2(String type, String keyword, String empno, String sdate, String edate, String iFlag) {

		BooleanBuilder builder = new BooleanBuilder();
		QElectronicApproval electronicApproval = QElectronicApproval.electronicApproval;
		
			LocalDate date = LocalDate.now();
			String maxDay = String.valueOf(date.withDayOfMonth(date.lengthOfMonth()));
			String minDay= String.valueOf(date.withDayOfMonth(01));
			
//			builder.and(electronicApproval.tno.gt(0));
			  
			builder.and(electronicApproval.timeAttendance.empno.eq(empno));

			if(iFlag != null) {
				builder.and(electronicApproval.timeAttendance.taday.between(minDay, maxDay));
				return builder;
			}
			
			if((sdate == null && edate == null) || (sdate == "" && edate == "")) {
			}else if((sdate != null && edate == null) || (sdate != "" && edate == "")){
				builder.and(electronicApproval.timeAttendance.taday.goe(sdate));
			}else if((sdate != null && edate != null) || (sdate != "" && edate != "")) {
				builder.and(electronicApproval.timeAttendance.taday.between(sdate, edate));
			}
					
			if(type != null && type != "") {
				builder.and(electronicApproval.eastatusno.eq(Long.valueOf(type)));
			}		
			    
		return builder;
	}
	
}
