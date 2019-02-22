package com.naturals.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.ElectronicApproval;
import com.naturals.domain.QTimeAttendance;
import com.naturals.domain.TAType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface ElectronicApprovalRepository extends CrudRepository<ElectronicApproval, String> , QuerydslPredicateExecutor<ElectronicApproval>{

	public default Predicate makePredicate(String type, String keyword, String empno) {
		   
		BooleanBuilder builder = new BooleanBuilder();
		QTimeAttendance timeAttendance = QTimeAttendance.timeAttendance;
		
		  builder.and(timeAttendance.tno.gt(0));
		  
		  
		  System.out.println("sysout: ::: ::::"+empno);

//		  아직 검색 없음
//		  builder.and(timeAttendance.empno.eq(empno));
		 
		  
//		  if(type==null) { return builder; }
		  
//		  switch(type) {
//		  case "d": 
//			  builder.and(timeAttendance.department.deptnm.like("%"+keyword+"%"));
//			  break;
//		  case "enm":
//			  builder.and(timeAttendance.employee.empno.like("%"+ keyword + "%"));
//			  break;
//		  case "eno":
//			  builder.and(timeAttendance.empno.like("%" + keyword + "%"));
//			  break; 
//		  case "m": 
//			  builder.and(timeAttendance.memo.like("%" + keyword + "%"));
//			  break;
//		  }
		  
		return builder;
	}
}
