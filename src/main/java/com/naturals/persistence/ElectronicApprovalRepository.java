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

	public default Predicate makePredicate(String type, String keyword, String empno, Boolean adminFlag) {
		   
		BooleanBuilder builder = new BooleanBuilder();
		QElectronicApproval electronicApproval = QElectronicApproval.electronicApproval;
		
//		  builder.and(electronicApproval.eano.gt(0));
		
		  System.out.println("EArepo makePredicate type, keyword, empno :::::::"+type+", "+keyword +","+ empno +", "+ adminFlag);

		  if(adminFlag == true && type == null) {
			  builder.and(electronicApproval.eastatusno.eq((long)1));
			  return builder;			  
		  }
		  
		  if(adminFlag == false && type == null) {
			  builder.and(electronicApproval.timeAttendance.empno.eq(empno));
			  return builder;
		  }
		  
		  if(adminFlag == false) {
			  builder.and(electronicApproval.timeAttendance.empno.eq(empno));
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
}
