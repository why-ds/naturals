package com.naturals.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.ElectronicApproval;
import com.naturals.domain.QElectronicApproval;
import com.naturals.domain.QTimeAttendance;
import com.naturals.domain.TAType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface ElectronicApprovalRepository extends CrudRepository<ElectronicApproval, Long> , QuerydslPredicateExecutor<ElectronicApproval>{
//ADMIN용
	public default Predicate makePredicate(String type, String keyword, String empno, Boolean adminFlag) {
		   
		BooleanBuilder builder = new BooleanBuilder();
		QElectronicApproval electronicApproval = QElectronicApproval.electronicApproval;
		
		  if(type == null) {
			  builder.and(electronicApproval.eastatusno.eq((long)1));
			  return builder;			  
		  }
		  
		  switch(type) {
		  case "status": 
			  builder.and(electronicApproval.eastatusno.eq(Long.parseLong(keyword)));
			  break;
//		  case "enm":
//			  builder.and(timeAttendance.employee.empno.like("%"+ keyword + "%"));
//			  break;
//		  case "eno":
//			  builder.and(timeAttendance.empno.like("%" + keyword + "%"));
//			  break; 
//		  case "m": 
//			  builder.and(timeAttendance.memo.like("%" + keyword + "%"));
//			  break;
		  }
		return builder;
	}
	
	
// USER용
	public default Predicate makePredicate2(String type, String keyword, String empno, Boolean adminFlag) {

		BooleanBuilder builder = new BooleanBuilder();
		QElectronicApproval electronicApproval = QElectronicApproval.electronicApproval;
		
		  System.out.println("EArepo makePredicate type, keyword, empno :::::::"+type+", "+keyword +","+ empno +", "+ adminFlag);

		 builder.and(electronicApproval.timeAttendance.empno.eq(empno));

		  if(type == null) {return builder;}
		  
		  switch(type) {
		  case "status": 
			  builder.and(electronicApproval.eastatusno.eq(Long.parseLong(keyword)));
			  break;
//		  case "enm":
//			  builder.and(timeAttendance.employee.empno.like("%"+ keyword + "%"));
//			  break;
//		  case "eno":
//			  builder.and(timeAttendance.empno.like("%" + keyword + "%"));
//			  break; 
//		  case "m": 
//			  builder.and(timeAttendance.memo.like("%" + keyword + "%"));
//			  break;
		  }
		return builder;
	}
	
}
