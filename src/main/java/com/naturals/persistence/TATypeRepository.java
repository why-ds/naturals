package com.naturals.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.naturals.domain.TAType;
import com.querydsl.core.BooleanBuilder;

public interface TATypeRepository  extends CrudRepository<TAType, Long>, QuerydslPredicateExecutor<TAType>{
}
