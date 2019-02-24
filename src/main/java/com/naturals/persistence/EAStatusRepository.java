package com.naturals.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.EAStatus;

public interface EAStatusRepository  extends CrudRepository<EAStatus, Long>, QuerydslPredicateExecutor<EAStatus>{
}
