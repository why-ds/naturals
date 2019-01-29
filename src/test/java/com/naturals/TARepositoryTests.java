package com.naturals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.naturals.domain.TimeAttendance;
import com.naturals.persistence.TimeAttendanceRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class TARepositoryTests {
	@Autowired
	TimeAttendanceRepository repo;
	
	@Test
	public void testList() {
		/*
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "tno");
		Page<TimeAttendance> result = repo.findAll(repo.makePredicate(null, null), pageable);
		log.info("PAGE : "+result.getPageable());
		log.info("============================");
		result.getContent().forEach(timeAttendance -> log.info(""+timeAttendance));
		*/
	}
	
	@Test
	public void testLIst2() {
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "tno");
		Page<TimeAttendance> result = repo.findAll(repo.makePredicate("dept", "0001", ""), pageable);

		log.info("PAGE : "+result.getPageable());
		log.info("============================");
		
		result.getContent().forEach(timeAttendance -> log.info(""+timeAttendance));
	}
	
}
